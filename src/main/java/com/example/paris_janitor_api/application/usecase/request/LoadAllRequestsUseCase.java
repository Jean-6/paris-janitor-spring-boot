package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.LoadAllRequestsPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class LoadAllRequestsUseCase implements LoadAllRequestsPort {

    private final com.example.paris_janitor_api.application.port.out.request.LoadAllRequestsPort loadAllRequestsPort;

    public LoadAllRequestsUseCase(com.example.paris_janitor_api.application.port.out.request.LoadAllRequestsPort loadAllRequestsPort) {
        this.loadAllRequestsPort = loadAllRequestsPort;
    }


    @Override
    public Flux<Request> getAllRequest() {
        return loadAllRequestsPort.findAll();
    }
}
