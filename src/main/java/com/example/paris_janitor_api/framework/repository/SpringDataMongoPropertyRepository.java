package com.example.paris_janitor_api.framework.repository;

import com.example.paris_janitor_api.framework.db.PropertyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoPropertyRepository extends MongoRepository<PropertyDocument,String> {
}
