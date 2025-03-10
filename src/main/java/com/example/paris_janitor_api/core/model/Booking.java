package com.example.paris_janitor_api.core.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "booking")
public class Booking {
    private String id;
    private String propertyId;
    private String userId;
    private LocalDateTime createdAt;

    /*private String weekNumber;
    private String dayOfWeek;
    private String hourOfDay;
    private String year;
    private  String[] fullDate ;*/

    public Booking(String id, String propertyId, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.propertyId = propertyId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
