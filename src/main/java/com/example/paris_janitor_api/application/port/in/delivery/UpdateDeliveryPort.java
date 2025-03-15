package com.example.paris_janitor_api.application.port.in.delivery;
import com.example.paris_janitor_api.core.model.Delivery;
import reactor.core.publisher.Mono;

public interface UpdateDeliveryPort {
    Mono<Delivery> updateDelivery(String id, Delivery delivery);
}
