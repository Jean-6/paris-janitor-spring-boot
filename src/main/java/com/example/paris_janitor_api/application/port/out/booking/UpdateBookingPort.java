package com.example.paris_janitor_api.application.port.out.booking;

import com.example.paris_janitor_api.core.model.Booking;

public interface UpdateBookingPort {
    Booking findByIdAndUpdate(String id, Booking booking);
}
