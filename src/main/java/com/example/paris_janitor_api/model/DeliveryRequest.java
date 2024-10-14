package com.example.paris_janitor_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "deliveryRequest")
public class DeliveryRequest implements InvoiceItem {
    @Id
    private String id;
    private String propertyId;
    private String type;
    private String description;
    private ArrayList<Stage> stage;
    @CreatedBy
    private String userId;
    private String providerId;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public double calculateTotalAmount() {
        return 0;
    }
}
