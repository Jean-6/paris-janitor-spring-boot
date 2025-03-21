package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.delivery.*;
import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class DeliveryByIdMongoAdapter implements DeleteDeliveryByIdPort,
        LoadDeliveryByIdPort,
        LoadDeliveriesPort,
        PersistDeliveryPort,
        UpdateDeliveryPort {

    private final DeliveryReactiveMongoRepo deliveryReactiveMongoRepo;

    public DeliveryByIdMongoAdapter(DeliveryReactiveMongoRepo deliveryReactiveMongoRepo) {
        this.deliveryReactiveMongoRepo = deliveryReactiveMongoRepo;
    }

    @Override
    public Mono<Delivery> deleteById(String id) {
        return deliveryReactiveMongoRepo.findById(id)
                .flatMap(existingDelivery -> deliveryReactiveMongoRepo.delete(existingDelivery)
                .then(Mono.just(existingDelivery)));
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
    public Mono<Delivery> saveDelivery(Delivery delivery) {
        return deliveryReactiveMongoRepo.save(delivery);
    }

    @Override
    public Mono<Delivery> findByIdAndUpdate(String id, Delivery delivery) {

        return deliveryReactiveMongoRepo.findById(id)
                .flatMap(existingDelivery -> {

                    existingDelivery.setDescription(delivery.getDescription());

                    return deliveryReactiveMongoRepo.save(existingDelivery);
                });
    }
}
