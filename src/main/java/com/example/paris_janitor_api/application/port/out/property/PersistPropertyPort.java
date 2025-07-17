package com.example.paris_janitor_api.application.port.out.property;

import com.example.paris_janitor_api.core.model.Property;

public interface PersistPropertyPort {
    Property saveProperty(Property property);
}
