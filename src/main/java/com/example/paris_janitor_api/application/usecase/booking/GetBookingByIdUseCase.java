package com.example.paris_janitor_api.application.usecase.booking;


import com.example.paris_janitor_api.application.port.in.booking.LoadBookingByIdPort;
import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetBookingByIdUseCase implements LoadBookingByIdPort {


    private final com.example.paris_janitor_api.application.port.out.booking.LoadBookingByIdPort loadBookingByIdPort;

    public GetBookingByIdUseCase(com.example.paris_janitor_api.application.port.out.booking.LoadBookingByIdPort loadBookingByIdPort) {
        this.loadBookingByIdPort = loadBookingByIdPort;
    }

    @Override
    public Booking getBookingById(String id) {
        return loadBookingByIdPort.findById(id);
    }

    @Override
    public List<Booking> getBookingByPropertyId(String id) {
        return loadBookingByIdPort.findByPropertyId(id);
    }
}
