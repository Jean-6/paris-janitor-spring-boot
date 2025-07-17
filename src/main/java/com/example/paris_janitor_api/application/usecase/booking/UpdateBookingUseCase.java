package com.example.paris_janitor_api.application.usecase.booking;

import com.example.paris_janitor_api.application.port.in.booking.UpdateBookingPort;
import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.stereotype.Service;



@Service
public class UpdateBookingUseCase implements UpdateBookingPort {

    private final com.example.paris_janitor_api.application.port.out.booking.UpdateBookingPort updateBookingPort;

    public UpdateBookingUseCase(com.example.paris_janitor_api.application.port.out.booking.UpdateBookingPort updateBookingPort) {
        this.updateBookingPort = updateBookingPort;
    }


    @Override
    public Booking updateBooking(String id, Booking booking) {
        return updateBookingPort.findByIdAndUpdate(id, booking);
    }
}
