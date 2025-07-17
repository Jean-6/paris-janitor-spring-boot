package com.example.paris_janitor_api.adapters.out.persistence;


import com.example.paris_janitor_api.application.port.out.request.*;
import com.example.paris_janitor_api.core.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RequestMongoAdapter implements
        DeleteByIdRequestPort,
        LoadRequestsPort,
        LoadRequestByIdPort,
        PersistRequestPort,
        UpdateRequestPort {

    private final RequestMongoRepo requestMongoRepo;

    public RequestMongoAdapter(RequestMongoRepo requestMongoRepo) {
        this.requestMongoRepo = requestMongoRepo;
    }

    @Override
    public  void deleteById(String id) {
        requestMongoRepo.deleteById(id);
    }

    @Override
    public List<Request> findAll() {
        return requestMongoRepo.findAll();
    }

    @Override
    public Request findById(String id) {

        return requestMongoRepo.findById(id)
                .orElseThrow();
    }

    @Override
    public Request saveRequest(Request request) {
        return requestMongoRepo.save(request);
    }

    @Override
    public Request findByIdAndUpdate(String id, Request request) {

        Request request1 = requestMongoRepo.findById(id)
                        .orElseThrow();

        request1.setPropertyId(request.getPropertyId());
        request1.setType(request.getType());
        request1.setDescription(request.getDescription());
        request1.setStages(request.getStages());
        request1.setUserId(request.getUserId());
       // request1.setProviderId();
        request1.setCreatedAt(request.getCreatedAt());

        return requestMongoRepo.save(request1);
    }
}
