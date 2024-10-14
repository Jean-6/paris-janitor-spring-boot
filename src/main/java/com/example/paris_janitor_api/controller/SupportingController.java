package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.exception.FileEmptyException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.Supporting;
import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.service.SupportingService;
import com.example.paris_janitor_api.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Log4j2
@RestController
@RequestMapping("/api/supporting")
public class SupportingController {

    @Autowired
    private SupportingService supportingService;
    @Autowired
    private UserService userService;

    @PostMapping(value="/upload",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Supporting> uploadSupporting(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userId") String userId) {

        try{
            Optional<User> user = userService.getUserById(userId);
            if(user.isEmpty()){
                throw new ResourceNotFoundException("User not found");
            }
            if(multipartFile.isEmpty()){
                throw new FileEmptyException("File is empty. Cannot save an empty file");//Or ResponseEntity No Content
            }
            //Tester si fichier vide ou mauvaise extension
            Supporting supporting = supportingService.insertSupporting(userId,multipartFile);
            return new ResponseEntity<>(supporting, HttpStatus.CREATED);
        }catch(Exception e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value="",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Supporting>> getAllUserSupporting( @RequestParam("userId") String userId) {

        try{
            Optional<User> user = userService.getUserById(userId);
            if(user.isEmpty()){
                throw new ResourceNotFoundException("User not found");
            }
            //Tester si fichier vide ou mauvaise extension
            List<Supporting> supporting = supportingService.findSupportings(userId);
            return new ResponseEntity<>(supporting, HttpStatus.CREATED);
        }catch(Exception e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

