package com.example.paris_janitor_api.application.port.in.delivery;

import reactor.core.publisher.Mono;

public interface DeleteDeliveryByIdPort {
    Mono<Void> deleteById(String id);
}
