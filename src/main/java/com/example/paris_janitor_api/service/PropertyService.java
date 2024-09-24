package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.Property;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
public interface PropertyService {
    Page <Property> getProperties(int page,int size);
    boolean existsPropertyById(String id);
    Property saveProperty(Property property) ;
    Optional<Property> getPropertyById(String propertyId);
    List<Property> getProperties();
    Property deleteProperty(String propertyId);
}
