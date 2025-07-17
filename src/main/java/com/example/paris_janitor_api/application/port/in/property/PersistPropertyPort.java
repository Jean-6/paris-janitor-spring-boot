package com.example.paris_janitor_api.application.port.in.property;

import com.example.paris_janitor_api.core.model.Property;

public interface PersistPropertyPort {

    Property save(Property property);
}
