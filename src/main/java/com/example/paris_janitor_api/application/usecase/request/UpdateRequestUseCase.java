package com.example.paris_janitor_api.application.usecase.request;


import com.example.paris_janitor_api.application.port.out.request.UpdateRequestPort;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Service;

@Service
public class UpdateRequestUseCase implements com.example.paris_janitor_api.application.port.in.request.UpdateRequestPort {

    private final UpdateRequestPort updateRequestPort;

    public UpdateRequestUseCase(UpdateRequestPort updateRequestPort) {
        this.updateRequestPort = updateRequestPort;
    }


    @Override
    public Request updateRequest(String id, Request request) {
        return updateRequestPort.findByIdAndUpdate(id, request);
    }

}
