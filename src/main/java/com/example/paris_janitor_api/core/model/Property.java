package com.example.paris_janitor_api.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "property")
public class Property {
    @Id
    private String id;
    private String type ;
    private double area;
    private long pieces;
    private double rent;
    private String description;
    private Address address;
    private String userId;
    private String tel;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private Status status;


    public Property(String id, String type, double area, long pieces, double rent, String description, Address address, String userId, String tel, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.area = area;
        this.pieces = pieces;
        this.rent = rent;
        this.description = description;
        this.address = address;
        this.userId = userId;
        this.tel = tel;
        this.createdAt = createdAt;
    }

    /*public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public long getPieces() {
        return pieces;
    }

    public double getRent() {
        return rent;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    public String getUserId() {
        return userId;
    }

    public String getTel() {
        return tel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }*/
}
