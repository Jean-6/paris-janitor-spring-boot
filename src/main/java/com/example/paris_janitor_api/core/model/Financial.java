package com.example.paris_janitor_api.core.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "financial")
public class Financial {
    private double rent;
}
