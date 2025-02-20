package com.example.paris_janitor_api.framework.repository;

import com.example.paris_janitor_api.framework.db.DeliveryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoDeliveryRepository extends MongoRepository<DeliveryDocument,String> {
}
