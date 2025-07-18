package com.example.paris_janitor_api.infrastructure.web;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T>{
    private boolean success;
    private String message;
    private T data;
    private int status;
    private String path;




    // --- Constructeurs utiles ---
    public ResponseWrapper(boolean success, String message, int status,String path, T data) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.path = path;
        this.data = data;
    }


    public ResponseWrapper(boolean success, int status, String path,T data) {
        this.success=success;
        this.status=status;
        this.path = path;
        this.data=data;
    }


    public  ResponseWrapper(boolean success,int status, String path) {
        this.success=success;
        this.status=status;
        this.path = path;
    }

    // --- Méthodes de fabrique statiques ---

    public static <T> ResponseWrapper<T> error(T data, String message, int status, String path) {
        return new ResponseWrapper<>(false, message,  status, path,data);
    }

    public static <T> ResponseWrapper<T> ok(String message,String path) {
        return new ResponseWrapper<>(true,  HttpStatus.OK.value(),path);
    }

    public static <T> ResponseWrapper<T> ok(String path,T data) {
        return new ResponseWrapper<>(true,HttpStatus.OK.value(),path,data);
    }

    public static <T> ResponseWrapper<T> ok( String message, String path,T data) {
        return new ResponseWrapper<>(true, message,  HttpStatus.OK.value(),path,data);
    }

    public static <T> ResponseWrapper<T> badRequest(String message) {
        return new ResponseWrapper<>(false, HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> ResponseWrapper<T> notFound(String message) {
        return new ResponseWrapper<>(false, HttpStatus.NOT_FOUND.value(), message, null);
    }

    public static <T> ResponseWrapper<T> conflict(String message) {
        return new ResponseWrapper<>(false, HttpStatus.CONFLICT.value(), message, null);
    }

    public static <T> ResponseWrapper<T> unauthorized(String message) {
        return new ResponseWrapper<>(false, HttpStatus.UNAUTHORIZED.value(), message, null);
    }

    public static <T> ResponseWrapper<T> internalServerError(String message) {
        return new ResponseWrapper<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }
}
