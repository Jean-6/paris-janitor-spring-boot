package com.example.paris_janitor_api.adapters.out.persistence;

import com.example.paris_janitor_api.application.port.out.property.*;
import com.example.paris_janitor_api.core.model.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class PropertyMongoAdapter implements LoadPropertiesPort,
        LoadByIdPropertyPort,
        DeletePropertyByIdPort,
        UpdatePropertyPort,
        PersistPropertyPort {


    private final PropertyMongoRepo propertyMongoRepo;

    public PropertyMongoAdapter(PropertyMongoRepo propertyMongoRepo) {
            this.propertyMongoRepo = propertyMongoRepo;
    }

    @Override
    public List<Property> loadAll() {
        return propertyMongoRepo.findAll();
    }

    @Override
    public Property findById(String id) {
        return propertyMongoRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(String id) {

        Property property =         propertyMongoRepo.findById(id)
                .orElseThrow();
        propertyMongoRepo.deleteById(property.getId());
    }

    @Override
    public Property saveProperty(Property property) {
        log.debug("Property mongo data : "+property);
        return propertyMongoRepo.save(property);
    }

    @Override
    public Property findByIdAndUpdate(String id, Property property) {

        Property property1 = propertyMongoRepo.findById(id)
                .orElseThrow();

        property1.setDetails(property.getDetails());
        property1.setFacilities(property.getFacilities());
        property1.setFinancial(property.getFinancial());

        return propertyMongoRepo.save(property1);
    }
}
