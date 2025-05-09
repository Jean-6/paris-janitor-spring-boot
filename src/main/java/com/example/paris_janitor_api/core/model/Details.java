package com.example.paris_janitor_api.core.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "details")
public class Details {
    private String type;
    private String address;
    private String city;
    private int area;
    private int peaces;
    private int rooms;
    private int bathrooms;
    private String description;
}
