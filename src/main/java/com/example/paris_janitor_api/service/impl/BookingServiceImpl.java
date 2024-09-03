package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.model.Booking;
import com.example.paris_janitor_api.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.awt.Color;
import java.util.Random;

@Service
@Slf4j
public class BookingServiceImpl  implements BookingService {
    @Override
    public Booking saveBooking(Booking booking) {
        return null;
    }

    @Override
    public Optional<Booking> getBookingById(String id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Booking> getBookings() {
        return null;
    }

    @Override
    public void deleteBooking(String id) {

    }

    @Override
    public Color getRandomColor() {
        Random random = new Random();

        // Generate random RGB values
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // Return a new Color object with the generated RGB values
        return new Color(red, green, blue);
    }
}
