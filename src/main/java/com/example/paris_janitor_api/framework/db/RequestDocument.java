package com.example.paris_janitor_api.framework.db;

import ch.qos.logback.core.status.Status;
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
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deliveryRequest")
public class RequestDocument {
    @Id
    private String id;
    private String propertyId;
    private String type;
    private String description;
    private List<StageDocument> stages;
    @CreatedBy
    private String userId;
    private String providerId;
    @CreatedDate
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
