package com.example.paris_janitor_api.repository;
import com.example.paris_janitor_api.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String>{
}
