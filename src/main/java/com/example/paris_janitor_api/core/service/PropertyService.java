package com.example.paris_janitor_api.core.service;


import com.example.paris_janitor_api.core.model.Property;

import java.util.List;

public interface PropertyService  {

        Property createProperty(Property property);
        List<Property> getAllProperties();
        Property getPropertyById(String id);


}
