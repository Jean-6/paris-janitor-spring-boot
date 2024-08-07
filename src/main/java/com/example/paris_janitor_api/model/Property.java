package com.example.paris_janitor_api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


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
    private List<Image>  images;
    private String userId;
}
