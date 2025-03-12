package com.example.paris_janitor_api.adapters.out.persistent;

import com.example.paris_janitor_api.application.port.out.delivery.*;
import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DeliveryByIdMongoAdapter extends MongoRepository<Delivery,String>,
        DeleteByIdDeliveryPort,
        LoadDeliveryByIdPort,
        LoadAllDeliveriesPort,
        PersistDeliveryPort/*,
        UpdateDeliveryPort*/ {

    @Override
    default void deleteDeliveryById(String id){

    }

    @Override
    default Optional<Delivery> loadDeliveryById(String id){
        return null;
    }

    @Override
    default List<Delivery> loadDeliveries(){
        return null;
    }
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
