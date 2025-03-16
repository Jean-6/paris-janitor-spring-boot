package com.example.paris_janitor_api.adapters.out.persistence;


import com.example.paris_janitor_api.application.port.out.request.*;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestMongoAdapter implements DeleteByIdRequestPort,
        LoadAllRequestsPort,
        LoadRequestByIdPort,
        PersistRequestPort,
        UpdateRequestPort {

    private final RequestReactiveMongoRepo requestReactiveMongoRepo;

    public RequestMongoAdapter(RequestReactiveMongoRepo requestReactiveMongoRepo) {
        this.requestReactiveMongoRepo = requestReactiveMongoRepo;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return requestReactiveMongoRepo.deleteById(id);
    }

    @Override
    public Flux<Request> findAll() {
        return requestReactiveMongoRepo.findAll();
    }

    @Override
    public Mono<Request> findById(String id) {
        return requestReactiveMongoRepo.findById(id);
    }

    @Override
    public Mono<Request> save(Request request) {
        return requestReactiveMongoRepo.save(request);
    }

    @Override
    public Mono<Request> update(String id, Request request) {
        return requestReactiveMongoRepo.findById(id)
                .flatMap(existingRequest -> {
                    existingRequest.setType(request.getType());

                    return requestReactiveMongoRepo.save(existingRequest);
                });
    }

}
