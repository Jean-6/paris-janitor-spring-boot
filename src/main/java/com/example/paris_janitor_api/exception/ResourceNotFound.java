package com.example.paris_janitor_api.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String o, String id) {
        super (o +" not found with ID : "+ id);
    }
}
