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
@Document(collection = "deliveryRequest")
public class DeliveryReq implements InvoiceItem {
    @Id
    private String id;
    private String userId;
    private String propertyId;
    private String type;
    private Status status;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "id='" + id + '\'' +
                ", clientName='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public double calculateTotalAmount() {
        return 0;
    }
}
