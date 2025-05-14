package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.booking.*;
import com.example.paris_janitor_api.core.model.Booking;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Component
public class BookingMongoAdapter implements
        PersistBookingPort,
        DeleteBookingByIdPort,
        LoadBookingsPort,
        LoadBookingByIdPort,
        UpdateBookingPort
{

    private final BookingReactiveMongoRepo bookingReactiveMongoRepo;

    public BookingMongoAdapter(BookingReactiveMongoRepo bookingReactiveMongoRepo) {
        this.bookingReactiveMongoRepo = bookingReactiveMongoRepo;
    }

    @Override
    public Mono<Booking> deleteById(String id) {
        return bookingReactiveMongoRepo.findById(id)
                .flatMap(existingBooking -> bookingReactiveMongoRepo.delete(existingBooking)
                .then(Mono.just(existingBooking)));
    }

    @Override
    public Flux<Booking> findAll() {
        return bookingReactiveMongoRepo.findAll();
    }


    @Override
    public Mono<Booking> findById(String id) {
        return bookingReactiveMongoRepo.findById(id);
    }

    @Override
    public Flux<Booking> findByPropertyId(String propertyId) {
        return bookingReactiveMongoRepo.findBookingByPropertyId(propertyId);
    }


    @Override
    public Mono<Booking> findByIdAndUpdate(String id, Booking booking) {
        return bookingReactiveMongoRepo.findById(id)
                .flatMap(existingBooking -> {
                    existingBooking.setUserId(booking.getUserId());
                    return bookingReactiveMongoRepo.save(existingBooking);
                });
    }

    @Override
    public Flux<Booking> saveBooking(Flux<Booking> booking) {
        return bookingReactiveMongoRepo.saveAll(booking);
    }
}
