package com.example.paris_janitor_api.application.port.out.property;

import com.example.paris_janitor_api.core.model.Property;

public interface UpdatePropertyPort {
    Property findByIdAndUpdate(String id, Property property);
}