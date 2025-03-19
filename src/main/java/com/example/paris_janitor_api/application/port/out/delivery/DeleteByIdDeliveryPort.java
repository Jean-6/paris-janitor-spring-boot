package com.example.paris_janitor_api.application.port.out.delivery;


import reactor.core.publisher.Mono;

public interface DeleteByIdDeliveryPort {
    Mono<Void> deleteById(String id);
}
