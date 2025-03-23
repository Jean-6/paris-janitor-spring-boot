package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.PersistRequestPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersistRequestUseCase implements PersistRequestPort {

    private final com.example.paris_janitor_api.application.port.out.request.PersistRequestPort persistRequestPort;

    public PersistRequestUseCase(com.example.paris_janitor_api.application.port.out.request.PersistRequestPort persistRequestPort) {
        this.persistRequestPort = persistRequestPort;
    }

    @Override
    public Mono<Request> saveRequest(Request request) {
        return  persistRequestPort.saveRequest(request);
    }
}
