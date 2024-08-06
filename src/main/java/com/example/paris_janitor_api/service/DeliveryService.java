package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.model.Delivery;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface DeliveryService {

    Delivery saveDelivery(Delivery delivery) ;
    Optional<Delivery> getDeliveryById(Integer deliveryId);
    Iterable<Delivery> getDelivery();
    void deleteDelivery(String deliveryId);
}
