package com.example.paris_janitor_api.dto;

import com.example.paris_janitor_api.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    @Id
    private String id;
    private String type ;
    private double area;
    private int pieces;
    private double rent;
    private String description;
    private Address address;
    private String userId;
    @CreatedDate
    private LocalDateTime createdAt;
}
