package com.example.paris_janitor_api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class SignupDto {

    private String firstname;
    private String surname;
    private String email;
    private String password;
}
