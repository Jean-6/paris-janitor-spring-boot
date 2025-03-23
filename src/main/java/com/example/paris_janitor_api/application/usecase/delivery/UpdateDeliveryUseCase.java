package com.example.paris_janitor_api.application.usecase.delivery;

import com.example.paris_janitor_api.application.port.in.delivery.UpdateDeliveryPort;
import com.example.paris_janitor_api.application.port.out.delivery.LoadDeliveryByIdPort;
import com.example.paris_janitor_api.application.port.out.delivery.PersistDeliveryPort;
import com.example.paris_janitor_api.core.model.Delivery;
import com.example.paris_janitor_api.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateDeliveryUseCase implements UpdateDeliveryPort {


    com.example.paris_janitor_api.application.port.out.delivery.UpdateDeliveryPort updateDeliveryPort;


    @Override
    public Mono<Delivery> updateDelivery(String id, Delivery delivery) {
        return updateDeliveryPort.findByIdAndUpdate(id, delivery);
    }
}
