package com.example.paris_janitor_api.dto;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class DeliveryDto {
    @Id
    private String id;
    private String type ;
    private String description;
    private double price;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String userId;
}
