package com.example.paris_janitor_api.application.usecase.property;

import com.example.paris_janitor_api.application.port.in.property.UpdatePropertyPort;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Service;

@Service
public class UpdatePropertyUseCase implements UpdatePropertyPort {

    private final com.example.paris_janitor_api.application.port.out.property.UpdatePropertyPort updatePropertyPort;


    public UpdatePropertyUseCase(com.example.paris_janitor_api.application.port.out.property.UpdatePropertyPort updatePropertyPort) {
        this.updatePropertyPort =  updatePropertyPort;
    }

    @Override
    public Property updateProperty(String id, Property property) {
        return updatePropertyPort.findByIdAndUpdate(id,property);
    }

}
