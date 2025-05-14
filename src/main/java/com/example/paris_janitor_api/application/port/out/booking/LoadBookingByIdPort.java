package com.example.paris_janitor_api.application.port.out.booking;



import com.example.paris_janitor_api.core.model.Booking;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface LoadBookingByIdPort {
    Mono<Booking> findById(String id);
    Flux<Booking> findByPropertyId(String propertyId);
}
