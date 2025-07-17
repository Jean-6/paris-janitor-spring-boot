package com.example.paris_janitor_api.application.usecase.property;


import com.example.paris_janitor_api.application.port.out.property.DeletePropertyByIdPort;
import org.springframework.stereotype.Service;


@Service
public class DeletePropertyByIdUseCase implements com.example.paris_janitor_api.application.port.in.property.DeletePropertyByIdPort {


private final DeletePropertyByIdPort deletePropertyByIdPort;

    public DeletePropertyByIdUseCase(DeletePropertyByIdPort deletePropertyByIdPort) {
        this.deletePropertyByIdPort = deletePropertyByIdPort;
    }

    @Override
    public void deleteById(String id) {
        deletePropertyByIdPort.deleteById(id);
    }
}
