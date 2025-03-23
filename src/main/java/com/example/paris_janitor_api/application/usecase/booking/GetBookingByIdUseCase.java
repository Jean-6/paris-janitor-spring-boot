package com.example.paris_janitor_api.application.usecase.booking;


import com.example.paris_janitor_api.application.port.in.booking.LoadBookingByIdPort;
import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class GetBookingByIdUseCase implements LoadBookingByIdPort {


    private final com.example.paris_janitor_api.application.port.out.booking.LoadBookingByIdPort loadBookingByIdPort;

    public GetBookingByIdUseCase(com.example.paris_janitor_api.application.port.out.booking.LoadBookingByIdPort loadBookingByIdPort) {
        this.loadBookingByIdPort = loadBookingByIdPort;
    }

    @Override
    public Mono<Booking> getBookingById(String id) {
        return loadBookingByIdPort.findById(id);
    }
}
