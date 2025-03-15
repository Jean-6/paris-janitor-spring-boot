package com.example.paris_janitor_api.application.port.in.booking;

import com.example.paris_janitor_api.core.model.Booking;
import reactor.core.publisher.Mono;

public interface LoadBookingByIdPort {
    Mono<Booking> getBookingById(String id);
}
