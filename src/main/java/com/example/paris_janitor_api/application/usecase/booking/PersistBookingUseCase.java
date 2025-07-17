package com.example.paris_janitor_api.application.usecase.booking;

import com.example.paris_janitor_api.application.port.in.booking.PersistBookingPort;
import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.stereotype.Service;


@Service
public class PersistBookingUseCase implements PersistBookingPort {

    private final com.example.paris_janitor_api.application.port.out.booking.PersistBookingPort persistBookingPort;

    public PersistBookingUseCase(com.example.paris_janitor_api.application.port.out.booking.PersistBookingPort persistBookingPort) {
        this.persistBookingPort = persistBookingPort;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return persistBookingPort.saveBooking(booking);
    }
}
