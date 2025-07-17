package com.example.paris_janitor_api.application.usecase.delivery;

import com.example.paris_janitor_api.application.port.in.delivery.DeleteDeliveryByIdPort;

import org.springframework.stereotype.Service;


@Service
public class DeleteDeliveryByIdUseCase implements DeleteDeliveryByIdPort {


    private com.example.paris_janitor_api.application.port.out.delivery.DeleteDeliveryByIdPort deleteDeliveryByIdPort;

    public DeleteDeliveryByIdUseCase(com.example.paris_janitor_api.application.port.out.delivery.DeleteDeliveryByIdPort deleteDeliveryByIdPort) {
        this.deleteDeliveryByIdPort = deleteDeliveryByIdPort;
    }

    @Override
    public void deleteById(String id) {
        deleteDeliveryByIdPort.deleteById(id);
    }
}
