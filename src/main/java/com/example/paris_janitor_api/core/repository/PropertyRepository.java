package com.example.paris_janitor_api.core.repository;

import com.example.paris_janitor_api.core.model.Property;

import java.util.List;

public interface PropertyRepository {

    Property save(Property property);
    List<Property> findAll() ;
    Property findById(String id);
}
