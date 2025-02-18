package com.example.paris_janitor_api.core.model;


import java.time.LocalDateTime;

public class Booking {
    private String id;
    private String propertyId;
    private String userId;
    private String weekNumber;
    private String dayOfWeek;
    private String hourOfDay;
    private String year;
    private  String[] fullDate ;
    private LocalDateTime createdAt;
}
