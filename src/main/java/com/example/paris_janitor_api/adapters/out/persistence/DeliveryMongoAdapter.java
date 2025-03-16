package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.delivery.*;
import com.example.paris_janitor_api.core.model.Delivery;;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class DeliveryMongoAdapter implements DeleteByIdDeliveryPort,
        LoadDeliveryByIdPort,
        LoadAllDeliveriesPort,
        PersistDeliveryPort,
        UpdateDeliveryPort {

    private final DeliveryReactiveMongoRepo deliveryReactiveMongoRepo;

    public DeliveryMongoAdapter(DeliveryReactiveMongoRepo deliveryReactiveMongoRepo) {
        this.deliveryReactiveMongoRepo = deliveryReactiveMongoRepo;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return deliveryReactiveMongoRepo.deleteById(id);
    }

    @Override
    public Flux<Delivery> findAll() {
        return deliveryReactiveMongoRepo.findAll();
    }

    @Override
    public Mono<Delivery> findById(String id) {
        return deliveryReactiveMongoRepo.findById(id);
    }

    @Override
    public Mono<Delivery> save(Delivery delivery) {
        return deliveryReactiveMongoRepo.save(delivery);
    }

    @Override
    public Mono<Delivery> update(String id, Delivery delivery) {
        return deliveryReactiveMongoRepo.findById(id)
                .flatMap(existingDelivery -> {

                    existingDelivery.setDescription(delivery.getDescription());

                    return deliveryReactiveMongoRepo.save(existingDelivery);
                });
    }
}
