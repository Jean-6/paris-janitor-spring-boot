package com.example.paris_janitor_api.infrastructure.exception;

import com.example.paris_janitor_api.infrastructure.config.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.lang.Exception;


@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleGenericException(Exception ex, ServerRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",ex.getMessage(),request.path()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException ex, ServerRequest request){
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        return ResponseEntity
                .status(HttpStatus.CONFLICT.value())
                .body(new ApiError(ex.getStatusCode().value(),
                        status.getReasonPhrase(),
                        ex.getReason(),
                        request.path()));
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleConflictException(ConflictException ex, ServerRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT.value())
                .body(new ApiError(HttpStatus.CONFLICT.value(), 
                        "Conflict",ex.getMessage(), request.path()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, ServerRequest serverRequest){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(new ApiError(HttpStatus.NOT_FOUND.value(),
                        "Not Found",ex.getMessage(),serverRequest.path()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleBadRequestException(IllegalArgumentException ex, ServerRequest serverRequest){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",ex.getMessage(),serverRequest.path()));
    }


}
