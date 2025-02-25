package com.example.paris_janitor_api.exception;

public class PropertyNotFoundException extends RuntimeException{
    public PropertyNotFoundException(String propertyId) {
        super ("Property not found with ID : "+ propertyId);
    }
}
