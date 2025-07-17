package com.example.paris_janitor_api.application.port.in.booking;

import com.example.paris_janitor_api.core.model.Booking;

public interface UpdateBookingPort {
    Booking updateBooking(String id, Booking booking);
}
