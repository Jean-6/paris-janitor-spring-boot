package com.example.paris_janitor_api.application.usecase.delivery;

import com.example.paris_janitor_api.application.port.in.delivery.DeleteDeliveryByIdPort;

import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class DeleteDeliveryByIdUseCase implements DeleteDeliveryByIdPort {


    private com.example.paris_janitor_api.application.port.out.delivery.DeleteDeliveryByIdPort deleteDeliveryByIdPort;

    public DeleteDeliveryByIdUseCase(com.example.paris_janitor_api.application.port.out.delivery.DeleteDeliveryByIdPort deleteDeliveryByIdPort) {
        this.deleteDeliveryByIdPort = deleteDeliveryByIdPort;
    }

    @Override
    public Mono<Delivery> deleteById(String id) {
        return deleteDeliveryByIdPort.deleteById(id);
    }
}
