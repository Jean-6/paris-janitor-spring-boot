package com.example.paris_janitor_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deliveryRequest")
public class DeliveryReq {
    @Id
    private String id;
    private String userId;
    private String type;
    private String status;

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "id='" + id + '\'' +
                ", clientName='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
