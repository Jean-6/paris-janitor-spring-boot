package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.dto.SignupDto;
import com.example.paris_janitor_api.dto.UserSearchDto;
import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.*;
import com.example.paris_janitor_api.service.UserService;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/active/{userId}",  consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<String> modifyStatus(@PathVariable String userId) {

        if(userId==null || userId.isEmpty()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try{
            UpdateResult result = userService.updateUserStatus(userId);
            if(result.getModifiedCount()>0){
                return ResponseEntity.status(HttpStatus.OK).body("Status updated");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        }catch(ResourceNotFoundException notFoundEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping(value = "/byCriteria",  consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<User>> getUsersByCriteria(@RequestBody UserSearchDto userSearchDto) {

        if(userSearchDto==null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try{
            List<User> users = userService.searchUserByCriteria(userSearchDto);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }catch(ResourceNotFoundException notFoundEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping(value = "/providers/availability/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<User>> getProvidersAvailability(@PathVariable String id) {

        if(id.isEmpty() || id.equals("null")){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
        }
        try{
            Optional<User> optionalUser = userService.getUserById(id);
            if(optionalUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Optional.empty());
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
        }catch(ResourceNotFoundException notFoundEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value = "/providers/availability/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProvidersAvailability(@RequestBody List<AvailabilityDay> availabilityDays,@PathVariable String id) {

        if(availabilityDays.isEmpty() || id.isEmpty() || id.equals("null")){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
        }
        try{
            Optional<User> optionalUser = userService.getUserById(id);
            if(optionalUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
            }
            optionalUser.get().setAvailabilityDays(availabilityDays);
            return ResponseEntity.status(HttpStatus.OK).body("Disponilitié(s) enregistrées");
        }catch(ResourceNotFoundException notFoundEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody SignupDto signupDto  ) {

        if(signupDto==null) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());

        try{
            Optional<User> optionalUser = userService.getUserByEmail(signupDto.getProfile().getEmail());
            if(optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cet email existe déjà");
            }
            User user = this.userService.saveUser(signupDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Compte utilisateur créé");
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Page<User>>> getUsersPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try{
            Page<User> userPage = userService.getUsersPerPage(page, size);
            if(userPage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(Optional.of(userPage));
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String id) {

        if(id.isEmpty() || id.equals("null")){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
        }
        try{
            Optional<User> optionalUser = userService.getUserById(id);
            if(optionalUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Optional.empty());
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
        }catch(ResourceNotFoundException notFoundEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        try{
            List<User> userList=userService.getUsers();
            if(userList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userList);
            }
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
