package com.example.paris_janitor_api.application.usecase.request;

import com.example.paris_janitor_api.application.port.in.request.LoadAllRequestsPort;
import com.example.paris_janitor_api.application.port.out.request.LoadRequestsPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllRequestsUseCase implements LoadAllRequestsPort {

    private final LoadRequestsPort loadRequestsPort;

    public GetAllRequestsUseCase(LoadRequestsPort loadRequestsPort) {
        this.loadRequestsPort = loadRequestsPort;
    }


    @Override
    public List<Request> getAllRequest() {
        return loadRequestsPort.findAll();
    }
}
