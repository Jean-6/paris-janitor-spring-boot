package com.example.paris_janitor_api.application.usecase.booking;

import com.example.paris_janitor_api.application.port.in.booking.UpdateBookingPort;
import com.example.paris_janitor_api.application.port.out.booking.LoadBookingByIdPort;
import com.example.paris_janitor_api.application.port.out.booking.PersistBookingPort;
import com.example.paris_janitor_api.core.model.Booking;
import com.example.paris_janitor_api.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Service
public class UpdateBookingUseCase implements UpdateBookingPort {

    private final com.example.paris_janitor_api.application.port.out.booking.UpdateBookingPort updateBookingPort;

    public UpdateBookingUseCase(com.example.paris_janitor_api.application.port.out.booking.UpdateBookingPort updateBookingPort) {
        this.updateBookingPort = updateBookingPort;
    }


    @Override
    public Mono<Booking> updateBooking(String id, Booking booking) {
        return updateBookingPort.findByIdAndUpdate(id, booking);
    }
}
