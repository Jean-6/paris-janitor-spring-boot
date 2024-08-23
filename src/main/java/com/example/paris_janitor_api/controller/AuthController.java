package com.example.paris_janitor_api.controller;


import com.example.paris_janitor_api.dto.SigninDto;
import com.example.paris_janitor_api.dto.SignupDto;
import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    /*@PostMapping(value="/signin",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(@RequestBody SigninDto signinDto) {
        log.debug(signinDto);
        User userLogged = userService.login(signinDto);
        if(userLogged!=null){
            return ResponseEntity.status(HttpStatus.OK).body(userLogged);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }*/


    @PostMapping(value = "/signup",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody SignupDto signupDto) {
        //try{
            if(userService.checkIfUserExistsByUsername(signupDto.getUsername())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ce login existe déjà");
            }
            if(userService.checkIfUserExistsByEmail(signupDto.getEmail())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cet email existe déjà");
            }
            // Create new user's account
            User user = userService.saveUser(signupDto);
            if(user!=null){
                return ResponseEntity.status(HttpStatus.CREATED).body("Compte utilisateur créé");
            }
        /*}catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }*/
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requete invalide");
    }
}
