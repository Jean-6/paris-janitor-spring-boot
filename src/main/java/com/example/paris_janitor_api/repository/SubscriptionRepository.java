package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
}
