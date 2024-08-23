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
@Document(collection = "plan")
public class Plan implements InvoiceItem{
    @Id
    private String id;
    private String name;
    private List<String> description;
    private Double monthlyPrice;
    private Double annualPrice;
    private String currency;

    @Override
    public double calculateTotalAmount() {
        return 0;
    }
}
