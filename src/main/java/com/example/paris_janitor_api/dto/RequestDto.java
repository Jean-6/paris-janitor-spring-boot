package com.example.paris_janitor_api.dto;

import com.example.paris_janitor_api.framework.entity.RequestStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class RequestDto {
    @Id
    private String id;
    private String userId;
    private String propertyId;
    private String type;
    private RequestStatus requestStatus;
    @CreatedDate
    private LocalDateTime createdAt;


}
