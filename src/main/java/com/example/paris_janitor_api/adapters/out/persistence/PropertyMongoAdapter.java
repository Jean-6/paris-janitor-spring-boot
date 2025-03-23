package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.property.*;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class PropertyMongoAdapter implements LoadPropertiesPort,
        LoadByIdPropertyPort,
        DeletePropertyByIdPort,
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
    public Mono<Property> deleteById(String id) {
        return propertyReactiveMongoRepo.findById(id)
                .flatMap(existingProperty -> propertyReactiveMongoRepo.delete(existingProperty)
                        .then(Mono.just(existingProperty)));
    }

    @Override
    public Mono<Property> saveBooking(Property property) {
        return propertyReactiveMongoRepo.save(property);
    }

    @Override
    public Mono<Property> findByIdAndUpdate(String id, Property property) {
        return propertyReactiveMongoRepo.findById(id)
                .flatMap(existingProperty -> {
                    existingProperty.setDescription(property.getDescription());

                    return propertyReactiveMongoRepo.save(existingProperty);
                });
    }
}
