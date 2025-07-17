package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.booking.*;
import com.example.paris_janitor_api.core.model.Booking;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BookingMongoAdapter implements
        PersistBookingPort,
        DeleteBookingByIdPort,
        LoadBookingsPort,
        LoadBookingByIdPort,
        UpdateBookingPort
{

    private final BookingMongoRepo bookingMongoRepo;

    public BookingMongoAdapter(BookingMongoRepo bookingMongoRepo) {
        this.bookingMongoRepo = bookingMongoRepo;
    }

    @Override
    public void deleteById(String id) {

        Booking booking = bookingMongoRepo.findById(id)
                .orElseThrow();
        bookingMongoRepo.delete(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingMongoRepo.findAll();
    }


    @Override
    public Booking findById(String id) {
        return bookingMongoRepo.findById(id)
                .orElseThrow();
    }

    @Override
    public List<Booking> findByPropertyId(String propertyId) {
        return bookingMongoRepo.findBookingByPropertyId(propertyId);
    }


    @Override
    public Booking findByIdAndUpdate(String id, Booking booking) {
        Booking booking1=bookingMongoRepo.findById(id)
                .orElseThrow();
        booking1.setPropertyId(booking.getPropertyId());
        booking1.setUserId(booking.getUserId());
        booking1.setStartDate(booking.getStartDate());
        booking1.setEndDate(booking.getEndDate());
        booking1.setCreatedAt(booking.getCreatedAt());

        return bookingMongoRepo.save(booking1);

    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingMongoRepo.save(booking);
    }
}
