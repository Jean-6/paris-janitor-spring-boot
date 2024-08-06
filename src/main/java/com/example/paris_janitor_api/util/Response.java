package com.example.paris_janitor_api.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    public static ResponseEntity<Object> result(String message, HttpStatus status, int count, Object responseOb){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message",message);
        map.put("status", status.value());
        map.put("data", responseOb);
        map.put("Total Character Count", count);

        return new ResponseEntity<Object>(map, status);


    }
}
