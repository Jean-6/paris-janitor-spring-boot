package com.example.paris_janitor_api.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "property")
public class Property {

    private String id;
    private String type;
    private String address;
    private String city;
    private int area;
    private int peaces;
    private int rooms;
    private int bathrooms;
    private boolean isFurnished;
    private boolean wifi;
    private boolean airConditioning;
    private boolean equippedKitchen;
    private boolean garage;
    private boolean outdoorSpaces;
    private double rent;
    private String description;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private String ownerId;
    private Status status;
}
