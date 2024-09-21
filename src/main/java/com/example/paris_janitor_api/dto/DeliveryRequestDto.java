package com.example.paris_janitor_api.dto;

import com.example.paris_janitor_api.model.RequestStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class DeliveryRequestDto {
    @Id
    private String id;
    private String userId;
    private String propertyId;
    private String type;
    private RequestStatus requestStatus;
    @CreatedDate
    private LocalDateTime createdAt;


}
