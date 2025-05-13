package com.example.paris_janitor_api.core.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "booking")
public class Booking {
    @Id
    private String id;
    private String propertyId;
    private String userId;
    private String startDate;
    private String endDate;
    private LocalDateTime createdAt;
}