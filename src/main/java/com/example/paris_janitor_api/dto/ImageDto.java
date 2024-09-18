package com.example.paris_janitor_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    @Id
    private String id;
    private String propertyId;
    private String filename;
    private String fileType;
    private long fileSize;
    private String contentType;
    private byte[] content;
    @CreatedDate
    private LocalDateTime createdAt;
    //private String url;
    //private String description;
}