package com.example.paris_janitor_api.application.port.in.booking;

import com.example.paris_janitor_api.core.model.Booking;


import java.util.List;

public interface LoadBookingByIdPort {
    Booking getBookingById(String id);
    List<Booking> getBookingByPropertyId(String id);
}
