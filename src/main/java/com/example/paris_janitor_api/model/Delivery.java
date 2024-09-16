package com.example.paris_janitor_api.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "delivery")
public class Delivery {
    @Id
    private String id;
    private String type ;
    private String description;
    private double price;
    @CreatedDate
    private LocalDateTime createdAt;
    

    @Override
    public String toString() {
        return "Service{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                //", createdAt=" + createdAt +
                //", status=" + status +
                '}';
        }

}
