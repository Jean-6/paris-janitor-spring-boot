package com.example.paris_janitor_api.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "property")
public class Property {
    @Id
    private String id;
    private String type ;
    private double area;
    private int pieces;
    private double rent;
    private String description;
    private Address address;
    @CreatedBy
    private String userId;
    //private String tel;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private PropertyStatus state = PropertyStatus.PENDING;
}
