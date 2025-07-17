package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.LoadRequestByIdPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;

@Service
public class GetRequestByIdUseCase implements LoadRequestByIdPort {

    private final com.example.paris_janitor_api.application.port.out.request.LoadRequestByIdPort loadRequestByIdPort;

    public GetRequestByIdUseCase(com.example.paris_janitor_api.application.port.out.request.LoadRequestByIdPort loadRequestByIdPort) {
        this.loadRequestByIdPort = loadRequestByIdPort;
    }

    @Override
    public Request getRequestById(String id) {
        return loadRequestByIdPort.findById(id);
    }

}
