package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.delivery.*;
import com.example.paris_janitor_api.core.model.Delivery;;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class DeliveryMongoAdapter implements DeleteByIdDeliveryPort, LoadDeliveryByIdPort, LoadAllDeliveriesPort, PersistDeliveryPort, UpdateDeliveryPort {
    @Override
    public void deleteDeliveryById(String id) {

    }

    @Override
    public Flux<Delivery> loadDeliveries() {
        return null;
    }

    @Override
    public Mono<Delivery> loadDeliveryById(String id) {
        return null;
    }

    @Override
    public Mono<Delivery> save(Delivery delivery) {
        return null;
    }

    @Override
    public Delivery updateDeliveryById(String id, Delivery delivery) {
        return null;
    }

   /* @Override
    void deleteDeliveryById(String id);

    @Override
    Mono<Delivery> loadDeliveryById(String id);

    @Override
    Flux<Delivery> loadDeliveries();

    @Override
    Mono<Delivery> save(Delivery delivery);

    /*@Override
    default void deleteDeliveryById(String id){

    }

    @Override
    default Optional<Delivery> loadDeliveryById(String id){
        return null;
    }

    @Override
    default List<Delivery> loadDeliveries(){
        return null;
    }*/
/*
    @Override
    default Delivery updateDeliveryById(String id, Delivery delivery){
        return null;
    }*/
    /*
     @Override
    public Delivery save(Delivery delivery) {

        DeliveryDocument deliveryDocument = new DeliveryDocument(delivery.getId(), delivery.getType(), delivery.getDescription(), delivery.getPrice(), delivery.getCreatedAt());
        deliveryDocument = springDataMongoDeliveryRepository.save(deliveryDocument);

        return new Delivery(deliveryDocument.getId(), deliveryDocument.getType(), deliveryDocument.getDescription(), deliveryDocument.getPrice(), deliveryDocument.getCreatedAt());
    }

    @Override
    public List<Delivery> findAll() {
        return springDataMongoDeliveryRepository.findAll().stream()
                .map(deliveryDocument -> new Delivery(deliveryDocument.getId(), deliveryDocument.getType(), deliveryDocument.getDescription(), deliveryDocument.getPrice(), deliveryDocument.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Delivery findById(String id) {
        DeliveryDocument deliveryDocument = springDataMongoDeliveryRepository.findById(id).orElse(null);
        if(deliveryDocument == null) return null;
        return new Delivery(deliveryDocument.getId(), deliveryDocument.getType(), deliveryDocument.getDescription(), deliveryDocument.getPrice(), deliveryDocument.getCreatedAt()
        );
    }
     */
}
