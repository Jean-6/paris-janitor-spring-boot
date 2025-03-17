package com.example.paris_janitor_api.application.port.in.booking;

import reactor.core.publisher.Mono;

public interface DeleteBookingByIdPort {
    Mono<Void> deleteById(String bookingId);
}
