package com.example.paris_janitor_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BookingDto {
    @Id
    private String id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String color;
}