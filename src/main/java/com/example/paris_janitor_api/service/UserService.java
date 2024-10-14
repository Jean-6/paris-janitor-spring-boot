package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.dto.SignupDto;
import com.example.paris_janitor_api.dto.UserSearchDto;
import com.example.paris_janitor_api.model.User;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<User> getUsersPerPage(int page, int size);
    Optional<User> getUserById(String id);
    List<User> getUsers();
    Optional<User> getUserByEmail(String email);
    User saveUser(SignupDto signupDto);
    List<User> searchUserByCriteria(UserSearchDto userSearchDto);

}
