package com.example.paris_janitor_api.framework.db;


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
@Document(collection = "booking")
public class BookingDocument {
    @Id
    private String id;
    private String propertyId;
    @CreatedBy
    private String userId;
    //private String weekNumber;
    //private String dayOfWeek;
    //private String hourOfDay;
   // private String year;
    //@JsonFormat(pattern="dd/MM/yyyy")
    //private  String[] fullDate ;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
