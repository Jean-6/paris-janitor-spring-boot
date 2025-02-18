package com.example.paris_janitor_api.framework.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.Exception;


@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> resourceNotFoundHandler(ResourceNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body("resource not found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> badRequestHandler(IllegalArgumentException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body("bad request");
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> conflictExceptionHandler(ConflictException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT.value())
                .body("conflict");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> runtimeExceptionException(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body("internal server error");
    }

}
