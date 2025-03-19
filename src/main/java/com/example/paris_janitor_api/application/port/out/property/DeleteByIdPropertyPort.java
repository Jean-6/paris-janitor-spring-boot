package com.example.paris_janitor_api.application.port.out.property;

import reactor.core.publisher.Mono;

public interface DeleteByIdPropertyPort {
    Mono<Void> deleteById(String id);
}
