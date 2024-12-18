package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropertyRepository extends MongoRepository<Property,String> {

    List<Property> findPropertiesByUserId(String userId);
    boolean existsPropertyById(String propertyId);
}
