package com.example.paris_janitor_api.framework.db;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "address")
public class AddressDocument {
    private String street ;
    private String city;
    private long zip;
}
