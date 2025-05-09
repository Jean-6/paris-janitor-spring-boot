package com.example.paris_janitor_api.core.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "owner")
public class Owner {

    private String ownerId;
    /*private String name;
    private String contact;
    private String email;*/
}
