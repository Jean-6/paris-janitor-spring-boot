package com.example.paris_janitor_api.application.port.in.booking;

import com.example.paris_janitor_api.core.model.Booking;

import java.util.Optional;

public interface LoadBookingByIdPort {
    Optional<Booking> getBookingById(String id);
}
