package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.Plan;
import com.example.paris_janitor_api.repository.PlanRepository;
import com.example.paris_janitor_api.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PlanRepository planRepository;

    @Override
    public Iterable<Plan> findAll() {
        try {
            return mongoTemplate.findAll(Plan.class);//planRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Optional<Plan> findById(String id) {
        return  Optional.ofNullable(planRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Plan save(Plan plan) {
        try {
            return mongoTemplate.save(plan);//planRepository.save(plan);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public void deleteById(String id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        Query query=new Query();
        query.addCriteria(new Criteria("id").is(plan.getId()));
        mongoTemplate.remove(query,Plan.class);//planRepository.deleteById(id);

    }
}
