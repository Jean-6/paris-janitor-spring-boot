package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return Optional.empty();
    }

    @Override
    public Iterable<User> getUser() {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }
}
