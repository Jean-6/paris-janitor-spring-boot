package com.example.paris_janitor_api.application.port.out.delivery;


import com.example.paris_janitor_api.core.model.Delivery;

public interface UpdateDeliveryPort {
    Delivery findByIdAndUpdate(String id, Delivery delivery);
}

