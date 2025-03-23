package com.example.paris_janitor_api.application.usecase.delivery;


import com.example.paris_janitor_api.application.port.in.delivery.PersistDeliveryPort;
import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class PersistDeliveryUseCase implements PersistDeliveryPort {


    private final com.example.paris_janitor_api.application.port.out.delivery.PersistDeliveryPort persistDeliveryPort;

    public PersistDeliveryUseCase(com.example.paris_janitor_api.application.port.out.delivery.PersistDeliveryPort persistDeliveryPort) {
        this.persistDeliveryPort = persistDeliveryPort;
    }

    @Override
    public Mono<Delivery> saveDelivery(Delivery delivery) {
        return persistDeliveryPort.saveDelivery(delivery);
    }
}
