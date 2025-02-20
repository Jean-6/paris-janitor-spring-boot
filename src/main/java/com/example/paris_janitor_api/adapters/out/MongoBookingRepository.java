package com.example.paris_janitor_api.adapters.out;

import com.example.paris_janitor_api.core.model.Booking;
import com.example.paris_janitor_api.core.repository.BookingRepository;
import com.example.paris_janitor_api.framework.db.BookingDocument;
import com.example.paris_janitor_api.framework.repository.SpringDataMongoBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoBookingRepository implements BookingRepository {

    private final SpringDataMongoBookingRepository springDataMongoBookingRepository;

    @Autowired
    public MongoBookingRepository(SpringDataMongoBookingRepository springDataMongoBookingRepository) {
        this.springDataMongoBookingRepository = springDataMongoBookingRepository;
    }

    @Override
    public Booking save(Booking booking) {
        BookingDocument bookingDocument = new BookingDocument(booking.getId(), booking.getPropertyId(), booking.getUserId(), booking.getCreatedAt());
        bookingDocument = springDataMongoBookingRepository.save(bookingDocument);
        return new Booking(bookingDocument.getId(), bookingDocument.getPropertyId(), bookingDocument.getUserId(), bookingDocument.getCreatedAt());
    }

    @Override
    public List<Booking> findAll() {
        return springDataMongoBookingRepository.findAll().stream()
                .map(bookingDocument -> new Booking(bookingDocument.getId(), bookingDocument.getPropertyId(), bookingDocument.getUserId(), bookingDocument.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Booking findById(String id) {
        BookingDocument bookingDocument = springDataMongoBookingRepository.findById(id).orElse(null);
        if(bookingDocument == null) return null;
        return new Booking(bookingDocument.getId(), bookingDocument.getPropertyId(), bookingDocument.getUserId(), bookingDocument.getCreatedAt());
    }
}
