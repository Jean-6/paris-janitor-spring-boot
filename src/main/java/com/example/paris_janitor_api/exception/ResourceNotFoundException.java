package com.example.paris_janitor_api.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String id) {
        super (id);
    }
}
