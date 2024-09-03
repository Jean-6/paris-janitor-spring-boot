package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.dto.SigninDto;
import com.example.paris_janitor_api.dto.SignupDto;
import com.example.paris_janitor_api.dto.UserDto;
import com.example.paris_janitor_api.model.Delivery;
import com.example.paris_janitor_api.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface UserService {

    boolean checkIfUserExistsByUsername(String login);
    boolean checkIfUserExistsByEmail(String email);
    User saveUser(SignupDto signupDto);
    //Optional<User> findUserByUsername(String username);
    //User login(SigninDto signinDto);
    /**/
    User saveUser(User user) ;
    Optional<User> getUserById(String userId);
    Iterable<User> getUsers();
    void deleteUser(String userId);
}
