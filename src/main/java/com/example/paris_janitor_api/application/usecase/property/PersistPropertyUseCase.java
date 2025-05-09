package com.example.paris_janitor_api.application.usecase.property;

import com.example.paris_janitor_api.application.port.in.property.PersistPropertyPort;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class PersistPropertyUseCase implements PersistPropertyPort {

    private final com.example.paris_janitor_api.application.port.out.property.PersistPropertyPort persistPropertyPort;

   PersistPropertyUseCase(com.example.paris_janitor_api.application.port.out.property.PersistPropertyPort persistPropertyPort) {
       this.persistPropertyPort = persistPropertyPort;
   }

    @Override
    public Mono<Property> save(Property property) {
       return persistPropertyPort.saveProperty(property);
    }
}
