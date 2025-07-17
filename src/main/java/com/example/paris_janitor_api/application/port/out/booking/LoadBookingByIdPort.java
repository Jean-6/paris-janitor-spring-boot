package com.example.paris_janitor_api.application.port.out.booking;



import com.example.paris_janitor_api.core.model.Booking;

import java.util.List;


public interface LoadBookingByIdPort {
    Booking findById(String id);
    List<Booking> findByPropertyId(String propertyId);
}
