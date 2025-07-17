package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.delivery.*;
import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DeliveryMongoAdapter implements DeleteDeliveryByIdPort,
        LoadDeliveryByIdPort,
        LoadDeliveriesPort,
        PersistDeliveryPort,
        UpdateDeliveryPort {

    private final DeliveryMongoRepo deliveryMongoRepo;

    public DeliveryMongoAdapter(DeliveryMongoRepo deliveryMongoRepo) {
        this.deliveryMongoRepo = deliveryMongoRepo;
    }

    @Override
    public void deleteById(String id) {

        Delivery delivery = deliveryMongoRepo.findById(id)
                .orElseThrow();
        deliveryMongoRepo.delete(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryMongoRepo.findAll();
    }

    @Override
    public Delivery findById(String id) {

        return deliveryMongoRepo.findById(id)
                .orElseThrow();
    }

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        return deliveryMongoRepo.save(delivery);
    }

    @Override
    public Delivery findByIdAndUpdate(String id, Delivery delivery) {

        Delivery delivery1 = deliveryMongoRepo.findById(id)
                .orElseThrow();
        delivery1.setType(delivery.getType());
        delivery1.setDescription(delivery.getDescription());
        delivery1.setPrice(delivery.getPrice());
        delivery1.setCreatedAt(delivery.getCreatedAt());

        return deliveryMongoRepo.save(delivery1);

    }
}
