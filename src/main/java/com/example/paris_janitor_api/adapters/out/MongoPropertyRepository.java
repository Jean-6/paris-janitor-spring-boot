package com.example.paris_janitor_api.adapters.out;

import com.example.paris_janitor_api.core.model.Address;
import com.example.paris_janitor_api.core.model.Property;
import com.example.paris_janitor_api.framework.db.PropertyDocument;
import com.example.paris_janitor_api.core.repository.PropertyRepository;
import com.example.paris_janitor_api.framework.repository.SpringDataMongoPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class MongoPropertyRepository implements PropertyRepository {

    private final SpringDataMongoPropertyRepository springDataMongoPropertyRepository;

    @Autowired
    public MongoPropertyRepository(SpringDataMongoPropertyRepository springDataMongoPropertyRepository) {
        this.springDataMongoPropertyRepository = springDataMongoPropertyRepository;
    }

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
}
