package com.example.paris_janitor_api.service.impl;


import com.example.paris_janitor_api.model.Property;
import com.example.paris_janitor_api.repository.PropertyRepository;
import com.example.paris_janitor_api.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PropertyRepository propertyRepository;


    @Override
    public Page<Property> getProperties(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return propertyRepository.findAll(pageable);
    }

    @Override
    public boolean existsPropertyById(String id) {
        Query query=new Query();
        query.addCriteria(new Criteria("id").is(id));
        return mongoTemplate.exists(query,Property.class);
    }

    @Override
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
        }

    @Override
    public Optional<Property> getPropertyById(String propertyId) {
        return propertyRepository.findById(propertyId);
    }

    @Override
    public List<Property> getProperties() {
        return mongoTemplate.findAll(Property.class);
    }

    @Override
    public Property deleteProperty(String propertyId) {
        Criteria criteria = new Criteria();
        criteria.and("id").is(propertyId);
        return mongoTemplate.findAndRemove(new Query().addCriteria(criteria),Property.class);
    }


}
