package com.example.paris_janitor_api.core.service;

import com.example.paris_janitor_api.core.model.Booking;

import java.util.List;

public interface BookingService {

        Booking createUser(Booking booking);
        List<Booking> getAllBookings();
        Booking getBookingById(String id);
}
