package com.example.paris_janitor_api.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Request {
    @Id
    private String id;
    private String propertyId;
    private String type;
    private String description;
    private ArrayList<Stage> stage;
    private String userId;
    private String providerId;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

}
