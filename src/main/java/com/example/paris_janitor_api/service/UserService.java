package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String userId);
    Iterable<User> getUser();
    void deleteUser(String userId);
}
