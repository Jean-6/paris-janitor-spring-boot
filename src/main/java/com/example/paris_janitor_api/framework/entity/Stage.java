package com.example.paris_janitor_api.framework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Stage {
    private RequestStatus status;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
