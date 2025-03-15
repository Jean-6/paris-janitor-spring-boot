package com.example.paris_janitor_api.application.port.in.property;
import com.example.paris_janitor_api.core.model.Property;
import reactor.core.publisher.Mono;

public interface UpdatePropertyPort {
    Mono<Property> updateProperty(String id, Property property);
}
