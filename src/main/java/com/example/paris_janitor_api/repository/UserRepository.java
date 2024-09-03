package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    boolean existsUserByEmail(String login);
    boolean existsUserByUsername(String username);
    Optional<User> findUserByUsername(String username);
    //User findUserByUsername(String username);
    Optional<User> findUserByUsernameAndPassword(String username,String password);
}
