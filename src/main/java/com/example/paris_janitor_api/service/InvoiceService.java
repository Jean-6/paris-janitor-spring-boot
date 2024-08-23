package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.Invoice;
import java.util.Optional;
public interface InvoiceService {
    Iterable<Invoice> findAll() ;
    Optional<Invoice> findById(String id);
    Invoice save(Invoice invoice);
    void deleteById(String id);
}
