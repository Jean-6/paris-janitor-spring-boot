package com.example.paris_janitor_api.application.usecase.property;


import com.example.paris_janitor_api.application.port.out.property.DeletePropertyByIdPort;
import com.example.paris_janitor_api.core.model.Property;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class DeletePropertyByIdUseCase implements com.example.paris_janitor_api.application.port.in.property.DeletePropertyByIdPort {


private final DeletePropertyByIdPort deletePropertyByIdPort;

    public DeletePropertyByIdUseCase(DeletePropertyByIdPort deletePropertyByIdPort) {
        this.deletePropertyByIdPort = deletePropertyByIdPort;
    }

    @Override
    public Mono<Property> deleteById(String id) {
        return deletePropertyByIdPort.deleteById(id);
    }
}
