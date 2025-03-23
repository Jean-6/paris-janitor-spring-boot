package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.LoadRequestByIdPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class LoadRequestByIdUseCase implements LoadRequestByIdPort {

    private final com.example.paris_janitor_api.application.port.out.request.LoadRequestByIdPort loadRequestByIdPort;

    public LoadRequestByIdUseCase(com.example.paris_janitor_api.application.port.out.request.LoadRequestByIdPort loadRequestByIdPort) {
        this.loadRequestByIdPort = loadRequestByIdPort;
    }

    @Override
    public Mono<Request> getRequestById(String id) {
        return loadRequestByIdPort.findById(id);
    }

}
