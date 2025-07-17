package com.example.paris_janitor_api.application.port.out.booking;

import com.example.paris_janitor_api.core.model.Booking;

import java.util.List;


public interface LoadBookingsPort {
    List<Booking> findAll();
}
