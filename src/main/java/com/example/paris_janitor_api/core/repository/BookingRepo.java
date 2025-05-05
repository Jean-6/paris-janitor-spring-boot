package com.example.paris_janitor_api.core.repository;

import com.example.paris_janitor_api.core.model.Booking;

import java.util.List;

public interface BookingRepo {

    Booking save(Booking booking);
    List<Booking> findAll() ;
    Booking findById(String id);
}
