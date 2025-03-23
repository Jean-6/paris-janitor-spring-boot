package com.example.paris_janitor_api.application.usecase.booking;


import com.example.paris_janitor_api.application.port.out.booking.LoadBookingsPort;
import com.example.paris_janitor_api.core.model.Booking;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class GetAllBookingsUseCase implements com.example.paris_janitor_api.application.port.in.booking.LoadAllBookingsPort {



    private final LoadBookingsPort loadBookingsPort;

    public GetAllBookingsUseCase(LoadBookingsPort loadBookingsPort) {
        this.loadBookingsPort = loadBookingsPort;
    }


    @Override
    public Flux<Booking> getAllBookings() {
        return loadBookingsPort.findAll();
    }
}
