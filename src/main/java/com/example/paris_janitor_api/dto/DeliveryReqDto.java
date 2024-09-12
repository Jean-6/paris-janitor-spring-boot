package com.example.paris_janitor_api.dto;

import com.example.paris_janitor_api.model.Status;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class DeliveryReqDto {
    @Id
    private String id;
    private String userId;
    private String propertyId;
    private String type;
    private Status status;
    private LocalDateTime createdAt;
}
