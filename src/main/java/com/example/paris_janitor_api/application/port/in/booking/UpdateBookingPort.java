package com.example.paris_janitor_api.application.port.in.booking;

import com.example.paris_janitor_api.core.model.Booking;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UpdateBookingPort {
    Mono<Booking> update(String id, Booking booking);
}
