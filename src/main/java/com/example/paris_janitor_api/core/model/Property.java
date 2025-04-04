package com.example.paris_janitor_api.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "property")
public class Property {
    @Id
    private String id;
    private String type ;
    private double area;
    private int numberPieces;
    private double rent;
    private String description;
    private Address address;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private Status status;
    private Owner owner;

}
