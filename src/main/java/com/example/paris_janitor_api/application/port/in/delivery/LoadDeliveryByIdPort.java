package com.example.paris_janitor_api.application.port.in.delivery;

import com.example.paris_janitor_api.core.model.Delivery;

import java.util.Optional;

public interface LoadDeliveryByIdPort {
    Optional<Delivery> getDeliveryById(String id);
}
