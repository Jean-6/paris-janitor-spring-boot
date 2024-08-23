package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.Plan;
import java.util.Optional;
public interface PlanService {
    Iterable<Plan> findAll() ;
    Optional<Plan> findById(String id);
    Plan save(Plan plan);
    void deleteById(String id);

}
