package com.example.paris_janitor_api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {
    private String email;
    private String amount;
    private String description;
}
