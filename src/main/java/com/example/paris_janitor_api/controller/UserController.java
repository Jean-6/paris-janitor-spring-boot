package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> save(@RequestBody User user) {
        return  ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> deleteDelivery(@PathVariable String userId) {
        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    /*@GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/")
    public Iterable<User> getUser(){
        return userService.getUser();
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @DeleteMapping("/{userId}")
    public  void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }


    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List <User> users = userService.getAllUser();
        if(users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
        }
        //if(!users.isEmpty()){
        return ResponseEntity.status(HttpStatus.OK).body(users);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable  String id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return null;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            userService.save(userDto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userOptional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return null;
    }*/

}
