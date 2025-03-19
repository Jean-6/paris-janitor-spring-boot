package com.example.paris_janitor_api.application.port.out.delivery;


import com.example.paris_janitor_api.core.model.Delivery;
import reactor.core.publisher.Mono;

public interface UpdateDeliveryPort {
    Mono<Delivery> findByIdAndUpdate(String id, Delivery delivery);
}

