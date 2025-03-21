package com.example.paris_janitor_api.application.port.out.booking;


import com.example.paris_janitor_api.core.model.Booking;
import reactor.core.publisher.Mono;

public interface DeleteBookingByIdPort {
    Mono<Booking> deleteById(String id);
}
