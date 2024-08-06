package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.dto.UserDto;
import com.example.paris_janitor_api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UserRepository extends MongoRepository<User,String> {

    List<User> findAll();

    User findUserByEmail(String email);

    User findUserById(String id);

    @Override
    <S extends User> S insert(S entity);

    @Override
    <S extends User> S save(S entity);

    @Override
    void deleteById(String id);

    //void updateUserByEmail(String id,UserDto userDto);

    //void updateUserById()
    //void updateUserById(String id, UserDto userDto);

}
