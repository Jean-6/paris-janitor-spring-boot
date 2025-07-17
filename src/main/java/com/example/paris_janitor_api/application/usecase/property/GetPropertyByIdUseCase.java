package com.example.paris_janitor_api.application.usecase.property;


import com.example.paris_janitor_api.application.port.in.property.LoadPropertyByIdPort;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Service;

@Service
public class GetPropertyByIdUseCase implements LoadPropertyByIdPort {


    private final com.example.paris_janitor_api.application.port.out.property.LoadByIdPropertyPort propertyPort;

    public GetPropertyByIdUseCase( com.example.paris_janitor_api.application.port.out.property.LoadByIdPropertyPort propertyPort) {
        this.propertyPort = propertyPort;
    }

    @Override
    public Property getPropertyById(String id) {
        return propertyPort.findById(id);

    }
}
