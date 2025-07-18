package com.example.paris_janitor_api.application.usecase.booking;

import com.example.paris_janitor_api.application.port.in.booking.DeleteBookingByIdPort;
import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.stereotype.Service;


@Service
public class DeleteBookingByIdUseCase implements DeleteBookingByIdPort {



    private final com.example.paris_janitor_api.application.port.out.booking.DeleteBookingByIdPort deleteBookingByIdPort;

    public DeleteBookingByIdUseCase(com.example.paris_janitor_api.application.port.out.booking.DeleteBookingByIdPort deleteBookingByIdPort) {
        this.deleteBookingByIdPort = deleteBookingByIdPort;
    }

    @Override
    public void deleteById(String bookingId) {
        deleteBookingByIdPort.deleteById(bookingId);
    }

}
