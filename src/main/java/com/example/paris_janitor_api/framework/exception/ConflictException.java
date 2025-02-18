package com.example.paris_janitor_api.framework.exception;

public class ConflictException  extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
