package com.example.paris_janitor_api.dto;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class DeliveryDto {
    @Id
    private String id;
    private String type ;
    private String description;
    private double price;
    private LocalDateTime createdAt;
}
