package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.model.Booking;

import java.util.Optional;

import java.awt.Color;
import java.util.Random;

public interface BookingService {
    Booking saveBooking(Booking booking) ;
    Optional<Booking> getBookingById(String id);
    Iterable<Booking> getBookings();
    void deleteBooking(String id);
    Color getRandomColor();
}
