package com.example.paris_janitor_api.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "booking")
public class Booking {
    private String id;
    private String propertyId;
    private String userId;
    //private LocalDateTime createdAt;
    /*private String weekNumber;
    private String dayOfWeek;
    private String hourOfDay;
    private String year;
    private  String[] fullDate ;*/

}
