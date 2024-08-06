package com.example.paris_janitor_api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Id
    private String id;

    private String fisrtname;
    private String surname;
    private String email;
    private String password;
    private String role;
    private boolean status;

    @Override
    public String toString() {
        return "UserDto{" +
                "fisrtname='" + fisrtname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                //", createdAt=" + createdAt +
                ", role='" + role + '\'' +
                ", status=" + status +
                '}';
    }
}
