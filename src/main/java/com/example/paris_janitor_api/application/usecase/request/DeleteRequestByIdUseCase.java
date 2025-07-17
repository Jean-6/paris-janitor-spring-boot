package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.DeleteRequestByIdPort;
import com.example.paris_janitor_api.application.port.out.request.DeleteByIdRequestPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteRequestByIdUseCase implements DeleteRequestByIdPort {

    private final DeleteByIdRequestPort deleteByIdRequestPort;

    public DeleteRequestByIdUseCase(DeleteByIdRequestPort deleteByIdRequestPort) {
        this.deleteByIdRequestPort = deleteByIdRequestPort;
    }


    @Override
    public void deleteById(String id) {
        deleteByIdRequestPort.deleteById(id);
    }
}
