package com.example.paris_janitor_api.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "invoice")
public class Invoice {
    @Id
    private String id;
    private String clientName;
    private String clientEmail;
    private List<InvoiceItem> products;
    private double totalAmount;
    private String status;
}
