package com.example.paris_janitor_api.application.port.out.request;

import reactor.core.publisher.Mono;

public interface DeleteByIdRequestPort {
    Mono<Void> deleteById(String id);
}