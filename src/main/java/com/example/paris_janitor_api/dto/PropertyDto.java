package com.example.paris_janitor_api.dto;

import com.example.paris_janitor_api.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {

    private String type ;
    private double area;
    private int pieces;
    private double rent;
    private String description;
    private Address address;
    private String userId;
    private LocalDateTime createdAt;
}
