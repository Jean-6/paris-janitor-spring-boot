package com.example.paris_janitor_api.application.port.out.booking;


import reactor.core.publisher.Mono;

public interface DeleteBookingByIdPort {
    Mono<Void> deleteById(String id);
}
