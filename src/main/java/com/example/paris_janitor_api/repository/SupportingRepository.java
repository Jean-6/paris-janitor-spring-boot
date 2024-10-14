package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.Supporting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupportingRepository extends MongoRepository<Supporting, String> {
}
