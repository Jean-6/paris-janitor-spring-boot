package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.property.*;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class PropertyMongoAdapter implements LoadAllPropertiesPort, LoadByIdPropertyPort, DeleteByIdPropertyPort, UpdatePropertyPort, PersistPropertyPort {
    @Override
    public void deleteById(String id) {

    }

    @Override
    public Flux<Property> loadAllProperties() {
        return null;
    }

    @Override
    public Mono<Property> loadPropertyBy(String id) {
        return null;
    }

    @Override
    public Mono<Property> updatePropertyById(String id, Property property) {
        return null;
    }

    @Override
    public Mono<Property> save(Property property) {
        return null;
    }
   /* @Override
    Mono<Property> save(Property property);

    @Override
    Flux<Property> loadAllProperties();

    @Override
    Mono<Property> loadPropertyBy(String id);


    @Override
    Mono<Property> updatePropertyById(String id, Property property);

    /*@Override
    default Optional<Property> loadPropertyBy(String id){
        return findById(id);
    }
    @Override
    default Property persistProperty(Property property){
        return save(property);
    }

    @Override
    default Property updatePropertyById(String id, Property property){
        property.setId(id);
        return save(property);
    }

    @Override
    default List<Property> loadAllProperties(){
        return findAll();
    }*/



/*

    @Override
    public Property save(Property property) {
        PropertyDocument propertyDocument = new PropertyDocument(property.getId(), property.getType(), property.getArea(), property.getPieces(), property.getRent(), property.getDescription(), new Address(property.getAddress().getStreet(),property.getAddress().getCity(),property.getAddress().getZip()), property.getUserId(), property.getTel(), property.getCreatedAt());
        propertyDocument = springDataMongoPropertyRepository.save(propertyDocument);
        return new Property(propertyDocument.getId(), propertyDocument.getType(), propertyDocument.getArea(), propertyDocument.getPieces(), propertyDocument.getRent(), propertyDocument.getDescription(), propertyDocument.getAddress(), propertyDocument.getUserId(), propertyDocument.getTel(), propertyDocument.getCreatedAt());
    }
    @Override
    public List<Property> findAll() {
        return springDataMongoPropertyRepository.findAll().stream()
                .map(propertyDocument -> new Property(propertyDocument.getId(), propertyDocument.getType(), propertyDocument.getArea(), propertyDocument.getPieces(), propertyDocument.getRent(), propertyDocument.getDescription(), propertyDocument.getAddress(), propertyDocument.getUserId(), propertyDocument.getTel(), propertyDocument.getCreatedAt())).collect(Collectors.toList());

    }
    @Override
    public Property findById(String id) {
        PropertyDocument propertyDocument = springDataMongoPropertyRepository.findById(id).orElse(null);
        if(propertyDocument==null) return null;
        return new Property(propertyDocument.getId(), propertyDocument.getType(), propertyDocument.getArea(),propertyDocument.getPieces(), propertyDocument.getRent(), propertyDocument.getDescription(), propertyDocument.getAddress(), propertyDocument.getUserId(), propertyDocument.getTel(), propertyDocument.getCreatedAt()
        );
    }
     */
}
