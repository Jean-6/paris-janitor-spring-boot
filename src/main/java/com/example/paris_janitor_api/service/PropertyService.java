package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.Property;
import java.util.Optional;
public interface PropertyService {
    boolean existsPropertyById(String id);
    Property saveProperty(Property property) ;
    Optional<Property> getPropertyById(String propertyId);
    Iterable<Property> getProperties();
    Iterable<Property> getMyProperties(String userId);

    void deleteProperty(String propertyId);
}
