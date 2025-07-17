package com.example.paris_janitor_api.application.usecase.property;


import com.example.paris_janitor_api.application.port.in.property.LoadAllPropertiesPort;
import com.example.paris_janitor_api.application.port.out.property.LoadPropertiesPort;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllPropertiesUseCase implements LoadAllPropertiesPort {

    private final LoadPropertiesPort loadPropertiesPort;

    public GetAllPropertiesUseCase(LoadPropertiesPort loadPropertiesPort) {
        this.loadPropertiesPort = loadPropertiesPort;
    }


    @Override
    public List<Property> getAllProperties() {
        return loadPropertiesPort.loadAll();
    }
}
