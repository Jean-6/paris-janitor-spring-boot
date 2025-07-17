package com.example.paris_janitor_api.application.usecase.delivery;


import com.example.paris_janitor_api.application.port.in.delivery.LoadDeliveryByIdPort;
import com.example.paris_janitor_api.application.port.out.booking.LoadBookingsPort;
import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.stereotype.Service;

@Service
public class GetDeliveryByIdUseCase implements LoadDeliveryByIdPort {


    private final com.example.paris_janitor_api.application.port.out.delivery.LoadDeliveryByIdPort loadDeliveryByIdPort;

    public GetDeliveryByIdUseCase(LoadBookingsPort loadBookingsPort, com.example.paris_janitor_api.application.port.out.delivery.LoadDeliveryByIdPort loadDeliveryByIdPort) {
        this.loadDeliveryByIdPort = loadDeliveryByIdPort;
    }

    @Override
    public Delivery getDeliveryById(String id) {
        return loadDeliveryByIdPort.findById(id);
    }

}
