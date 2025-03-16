package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DeliveryReactiveMongoRepo extends ReactiveMongoRepository<Delivery, String> {
}
