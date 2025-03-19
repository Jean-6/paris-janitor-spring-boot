package com.example.paris_janitor_api.application.port.in.property;

import reactor.core.publisher.Mono;

public interface DeletePropertyByIdPort {
    Mono<Void> deleteById(String id);
}
