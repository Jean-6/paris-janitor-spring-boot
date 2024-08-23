package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.Delivery;
import java.util.Optional;
public interface DeliveryService {
    Delivery saveDelivery(Delivery delivery) ;
    Optional<Delivery> getDeliveryById(String deliveryId);
    Iterable<Delivery> getDeliveries();
    void deleteDelivery(String deliveryId);
}
