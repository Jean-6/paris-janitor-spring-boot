package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.dto.SignupDto;
import com.example.paris_janitor_api.dto.UserSearchDto;
import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.repository.UserRepository;
import com.example.paris_janitor_api.service.UserService;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;




import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Page<User> getUsersPerPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User saveUser(SignupDto signupDto) {
        User user=modelMapper.map(signupDto, User.class);
        return mongoTemplate.insert(user);
    }

    @Override
    public List<User> searchUserByCriteria(UserSearchDto userSearchDto) {

        Query query = new Query();
        if(userSearchDto.getEmail()!=null && !userSearchDto.getEmail().isEmpty()){
            query.addCriteria(Criteria.where("profile.email").is(userSearchDto.getEmail()));
        }
        if(userSearchDto.getUsername()!=null && !userSearchDto.getUsername().isEmpty()){
            query.addCriteria(Criteria.where("profile.username").is(userSearchDto.getUsername()));
        }
        if(userSearchDto.getRole()!=null && !userSearchDto.getRole().isEmpty()){
            switch (userSearchDto.getRole()){
                case "ADMIN":
                    query.addCriteria(Criteria.where("roles").is("ADMIN"));
                    break;
                case "LESSOR":
                    query.addCriteria(Criteria.where("roles").is("LESSOR"));
                    break;
                case "TRAVELER":
                    query.addCriteria(Criteria.where("roles").is("TRAVELER"));
                    break;
                case "PROVIDER":
                    query.addCriteria(Criteria.where("roles").is("PROVIDER"));
                default:
                    break;
            }
        }
        if( userSearchDto.isStatus()){
            query.addCriteria(Criteria.where("active").is(true));
        }
        if( !userSearchDto.isStatus()){
            query.addCriteria(Criteria.where("active").is(false));
        }
        return mongoTemplate.find(query,User.class);
    }

    @Override
    public UpdateResult updateUserStatus(String id) {

        Optional<User> user= userRepository.findById(id);

        Query query = new Query();
        Update update = new Update();
        if(user.isPresent()){
            query.addCriteria(Criteria.where("id").is(user.get().getId()));
            if(user.get().isActive()){
                update.set("active",false);
            }else{
                update.set("active",true);
            }
        }
        return mongoTemplate.updateFirst(query,update,User.class);
    }


}
