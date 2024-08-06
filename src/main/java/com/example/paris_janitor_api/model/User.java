package com.example.paris_janitor_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private String id;

    @Field(value = "firstname")
    private String fisrtname;
    @Field(value = "surname")
    private String surname;
    @Field(value = "email")
    private String email;
    @Field(value = "password")
    private String password;
    @Field(value = "createdAt")
    private LocalDateTime createdAt;
    @Field(value = "role")
    private String role;
    @Field(value = "status")
    private boolean status;

    @Override
    public String toString() {
        return "User{" +
                // "id='" + id + '\'' +
                ", fisrtname='" + fisrtname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", role='" + role + '\'' +
                ", status=" + status +
                '}';
    }
}
