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

    private final BookingReactiveMongoRepo bookingReactiveMongoRepo;

    public BookingMongoAdapter(BookingReactiveMongoRepo bookingReactiveMongoRepo) {
        this.bookingReactiveMongoRepo = bookingReactiveMongoRepo;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookingReactiveMongoRepo.deleteById(id);
    }

    @Override
    public Flux<Booking> loadBookings() {
        return bookingReactiveMongoRepo.findAll();
    }

    @Override
    public Mono<Booking> getBookingById(String id) {
        return bookingReactiveMongoRepo.findById(id);
    }

    @Override
    public Mono<Booking> updateBookingById(String id, Booking booking) {
        return bookingReactiveMongoRepo.findById(id)
                .flatMap(existingBooking -> {
                    existingBooking.setUserId(booking.getUserId());
                    return bookingReactiveMongoRepo.save(existingBooking);
                });
    }

    @Override
    public Mono<Booking> save(Booking booking) {
        return bookingReactiveMongoRepo.save(booking);
    }
}
