package com.example.paris_janitor_api.framework.repository;


import com.example.paris_janitor_api.framework.db.RequestDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoRequestRepository extends MongoRepository<RequestDocument,String> {
}
