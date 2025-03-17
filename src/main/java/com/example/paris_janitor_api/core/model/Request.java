package com.example.paris_janitor_api.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Document(collection = "request")
public class Request {
    @Id
    private String id;
    private String propertyId;
    private String type;
    private String description;
    private List<Stage> stages;
    private String userId;
    private String providerId;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    public Request(String id, String propertyId, String type, String description, List<Stage> stages, String userId, String providerId, LocalDateTime createdAt) {
        this.id = id;
        this.propertyId = propertyId;
        this.type = type;
        this.description = description;
        this.stages = stages;
        this.userId = userId;
        this.providerId = providerId;
        this.createdAt = createdAt;
    }
}
