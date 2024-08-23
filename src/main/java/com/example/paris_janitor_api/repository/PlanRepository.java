package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends MongoRepository<Plan, String> {
    Optional<Plan> findById();
}
