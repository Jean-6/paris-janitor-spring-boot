package com.example.paris_janitor_api.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
        super (msg);
    }
}
