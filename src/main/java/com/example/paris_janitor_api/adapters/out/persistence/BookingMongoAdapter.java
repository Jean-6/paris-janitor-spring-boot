package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.booking.*;
import com.example.paris_janitor_api.core.model.Booking;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class BookingMongoAdapter implements PersistBookingPort,
        DeleteBookingByIdPort,
        LoadAllBookingsPort,
        LoadBookingByIdPort,
        UpdateBookingPort
{
    @Override
    public Mono<Booking> persistBooking(Booking booking) {
        return null;
    }

    @Override
    public Mono<Void> deleteBookingById(String id) {
        return null;
    }

    @Override
    public Flux<Booking> loadBookings() {
        return null;
    }

    @Override
    public Mono<Booking> getBookingById(String id) {
        return null;
    }

    @Override
    public Booking updateBookingById(String id, Booking booking) {
        return null;
    }



    /*@Override
    default Flux<Booking> loadBookings(){
        return findAll();
    }

    @Override
    default Mono<Booking> persistBooking(Booking booking){
        return Mono.just(booking);
    }

    default Mono<Booking> loadBookingByIdPort(String idPort){
        return findById(idPort);
    }

    @Override
    Mono<Void> deleteBookingById(String id);*/

/*
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
 */
}
