package com.example.paris_janitor_api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subscription")
public class Subscription implements InvoiceItem{
    @Id
    private String id;
    private String userId;
    private Plan plan;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Override
    public double calculateTotalAmount() {
        return 0;
    }
}
