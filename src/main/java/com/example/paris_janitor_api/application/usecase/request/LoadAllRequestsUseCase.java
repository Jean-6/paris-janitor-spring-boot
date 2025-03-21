package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.LoadAllRequestsPort;
import com.example.paris_janitor_api.application.port.out.request.LoadRequestsPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class LoadAllRequestsUseCase implements LoadAllRequestsPort {

    private final LoadRequestsPort loadRequestsPort;

    public LoadAllRequestsUseCase(LoadRequestsPort loadRequestsPort) {
        this.loadRequestsPort = loadRequestsPort;
    }


    @Override
    public Flux<Request> getAllRequest() {
        return loadRequestsPort.findAll();
    }
}
