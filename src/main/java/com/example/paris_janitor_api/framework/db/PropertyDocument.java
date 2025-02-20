package com.example.paris_janitor_api.framework.db;


import com.example.paris_janitor_api.core.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "property")
public class PropertyDocument {
    @Id
    private String id;
    private String type ;
    private double area;
    private long pieces;
    private double rent;
    private String description;
    private Address address;
    @CreatedBy
    private String userId;
    private String tel;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    //private PropertyStatus state = PropertyStatus.PENDING;*/
}
