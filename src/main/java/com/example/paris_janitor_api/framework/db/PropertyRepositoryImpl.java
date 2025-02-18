package com.example.paris_janitor_api.framework.db;

import com.example.paris_janitor_api.framework.entity.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PropertyRepositoryImpl extends MongoRepository<Property,String> {

    //List<Property> findPropertiesByUserId(String userId);
    //boolean existsPropertyById(String propertyId);
}
