package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.Property;
import com.example.paris_janitor_api.repository.PropertyRepository;
import com.example.paris_janitor_api.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public boolean existsPropertyById(String id) {
        Query query=new Query();
        query.addCriteria(new Criteria("id").is(id));
        return mongoTemplate.exists(query,Property.class);
    }

    @Override
    public Property saveProperty(Property property) {
        try {
            return propertyRepository.save(property);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public Optional<Property> getPropertyById(String propertyId) {
        return  Optional.ofNullable(propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(propertyId)));
    }

    @Override
    public Iterable<Property> getProperties() {
        try {
            return propertyRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void deleteProperty(String propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(propertyId));
        propertyRepository.deleteById(propertyId);
    }
}
