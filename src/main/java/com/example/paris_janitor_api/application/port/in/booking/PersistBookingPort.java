package com.example.paris_janitor_api.application.port.in.booking;

import com.example.paris_janitor_api.core.model.Booking;
import reactor.core.publisher.Flux;

public interface PersistBookingPort {
    Flux<Booking> saveBooking(Flux<Booking> booking) ;
}
