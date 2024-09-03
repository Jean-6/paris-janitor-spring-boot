package com.example.paris_janitor_api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private String role;
    private boolean active;

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
