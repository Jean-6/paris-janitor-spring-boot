package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.DeleteRequestByIdPort;
import com.example.paris_janitor_api.application.port.out.request.DeleteByIdRequestPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteRequestByIdUseCase implements DeleteRequestByIdPort {

    private final DeleteByIdRequestPort deleteByIdRequestPort;

    public DeleteRequestByIdUseCase(DeleteByIdRequestPort deleteByIdRequestPort) {
        this.deleteByIdRequestPort = deleteByIdRequestPort;
    }


    @Override
    public Mono<Request> deleteById(String id) {
        return deleteByIdRequestPort.deleteById(id);
    }
}
