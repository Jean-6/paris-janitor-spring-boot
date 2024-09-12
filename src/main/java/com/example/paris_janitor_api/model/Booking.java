package com.example.paris_janitor_api.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking")
public class Booking {
    //@Id
    //private String id;
    @NotNull
    private String propertyId;
    @NotNull
    private String userId;
    @NotNull
    private int weekNumber;
    @NotNull
    private String dayOfWeek;
    @NotNull
    private String hourOfDay;
    @NotNull
    private LocalDateTime bookingDate;
    @NotNull
    private String color;
}
