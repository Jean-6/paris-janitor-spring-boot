package com.example.paris_janitor_api.core.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "facilities")
public class Facilities {
    private boolean isFurnished;
    private boolean wifi;
    private boolean airConditioning;
    private boolean equippedKitchen;
    private boolean garage;
    private boolean outdoorSpaces;
}
