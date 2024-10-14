package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.model.Booking;

import java.util.List;
import java.util.Optional;


public interface BookingService {
    Booking saveBooking(Booking booking) ;
    Optional<Booking> getBookingById(String id);
    List<Booking> getBookings();
    Optional<Booking> deleteBooking(String id);
    List<Booking> getBookingBy(String id,String week,String year) ;
    List <Booking> getBookingByUserId(String userId);

}
