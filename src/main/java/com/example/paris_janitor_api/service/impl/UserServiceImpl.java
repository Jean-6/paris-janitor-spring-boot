package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.dto.SignupDto;
import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.repository.UserRepository;
import com.example.paris_janitor_api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    /*@Autowired
    private PasswordEncoder passwordEncoder;
*/
    @Override
    public boolean checkIfUserExistsByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean checkIfUserExistsByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User saveUser(SignupDto signupDto) {
        User user = new User("",signupDto.getUsername(),
                signupDto.getEmail(),
                "",//passwordEncoder.encode(signupDto.getPassword()),
                LocalDateTime.now(), "", false);
        return mongoTemplate.save(user);
    }

    /*@Override
    public Optional<User> findUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        return user;
    }*/

    /*@Override
    public User login(SigninDto signinDto) {

        Query query=new Query();
        query.addCriteria(new Criteria("username").is(signinDto.getUsername()));
        User user1 = mongoTemplate.findOne(query,User.class);
        if(user1!=null){
            String password = signinDto.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight=true;//Boolean isPwdRight = passwordEncoder.matches(password,encodedPassword);
            if(isPwdRight){
                Query query1=new Query();
                query1.addCriteria(new Criteria("username").is(signinDto.getUsername())
                        .andOperator(new Criteria("password").is(encodedPassword)));
                User user = mongoTemplate.findOne(query,User.class);
                if(user!=null){
                    return user;
                }else{
                    return null;
                }
            }
        }
        return null;
    }*/

    /**/
    @Override
    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }
    @Override
    public Optional<User> getUserById(String userId) {
        return  Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId)));
    }
    @Override
    public Iterable<User> getUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId));
        userRepository.deleteById(user.getId());
    }
    /**/
}
