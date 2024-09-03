package com.example.paris_janitor_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "image")
public class Image {
    //private String url;
    @Id
    private String id;
    private String propertyId;
    private String filename;
    private String fileType;
    private long fileSize;
    private String contentType;
    private byte[] content;
}
