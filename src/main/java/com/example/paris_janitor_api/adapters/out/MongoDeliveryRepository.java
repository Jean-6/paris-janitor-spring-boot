package com.example.paris_janitor_api.adapters.out;

import com.example.paris_janitor_api.core.model.Delivery;
import com.example.paris_janitor_api.core.repository.DeliveryRepository;
import com.example.paris_janitor_api.framework.db.DeliveryDocument;
import com.example.paris_janitor_api.framework.repository.SpringDataMongoDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoDeliveryRepository implements DeliveryRepository {

    private final SpringDataMongoDeliveryRepository springDataMongoDeliveryRepository;

    @Autowired
    public MongoDeliveryRepository(SpringDataMongoDeliveryRepository springDataMongoDeliveryRepository) {
        this.springDataMongoDeliveryRepository = springDataMongoDeliveryRepository;
    }

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
}
