package com.example.paris_janitor_api.application.usecase.delivery;

import com.example.paris_janitor_api.application.port.in.delivery.UpdateDeliveryPort;
import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.stereotype.Service;


@Service
public class UpdateDeliveryUseCase implements UpdateDeliveryPort {


    com.example.paris_janitor_api.application.port.out.delivery.UpdateDeliveryPort updateDeliveryPort;


    @Override
    public Delivery updateDelivery(String id, Delivery delivery) {
        return updateDeliveryPort.findByIdAndUpdate(id, delivery);
    }
}
