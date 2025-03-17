package com.example.paris_janitor_api.adapters.in.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String code;
    private String details;
}
/*
{
  "status": 400,
  "error": "Invalid product ID",
  "code": "BAD_REQUEST",
  "details": "The provided product ID is not valid"
}
 */