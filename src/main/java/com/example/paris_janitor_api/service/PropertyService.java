package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.dto.PropertySearchDto;
import com.example.paris_janitor_api.model.Property;
import com.mongodb.client.result.UpdateResult;
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
    List<Property> getPropertyByUserId(String userId);
    UpdateResult updatePropertyStatus(String id);
    List<Property> searchPropertyByCriteria(PropertySearchDto properSearchDto);
}
