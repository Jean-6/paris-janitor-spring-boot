package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.property.*;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class PropertyMongoAdapter implements LoadAllPropertiesPort,
        LoadByIdPropertyPort,
        DeleteByIdPropertyPort,
        UpdatePropertyPort,
        PersistPropertyPort {


    private final PropertyReactiveMongoRepo propertyReactiveMongoRepo;

    public PropertyMongoAdapter(PropertyReactiveMongoRepo propertyReactiveMongoRepo) {
            this.propertyReactiveMongoRepo = propertyReactiveMongoRepo;
    }

    @Override
    public Flux<Property> loadAll() {
        return propertyReactiveMongoRepo.findAll();
    }

    @Override
    public Mono<Property> loadById(String id) {
        return propertyReactiveMongoRepo.findById(id);
    }

    @Override
    public Mono<Property> save(Property property) {
        return propertyReactiveMongoRepo.save(property);
    }

    @Override
    public Mono<Property> update(String id, Property property) {
        return propertyReactiveMongoRepo.findById(id)
                .flatMap(existingProperty -> {
                    existingProperty.setDescription(property.getDescription());

                    return propertyReactiveMongoRepo.save(existingProperty);
                });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return propertyReactiveMongoRepo.deleteById(id);
    }

}
