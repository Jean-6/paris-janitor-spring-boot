package com.example.paris_janitor_api.application.port.out.property;

import com.example.paris_janitor_api.core.model.Property;
import reactor.core.publisher.Mono;

public interface DeletePropertyByIdPort {
    Mono<Property> deleteById(String id);
}
