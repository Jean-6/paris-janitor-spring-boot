package com.example.paris_janitor_api.application.port.in.property;

import com.example.paris_janitor_api.core.model.Property;

import java.util.List;

public interface LoadAllPropertiesPort {
    List<Property> getAllProperties();
}
