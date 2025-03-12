package com.example.paris_janitor_api.adapters.out.persistence;


import com.example.paris_janitor_api.application.port.out.request.*;
import com.example.paris_janitor_api.core.model.Request;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestMongoAdapter implements DeleteByIdRequestPort,
        LoadAllRequestsPort,
        LoadRequestByIdPort,
        PersistRequestPort,
        UpdateRequestPort {
    @Override
    public Mono<Void> deleteRequestById(String id) {
        return null;
    }

    @Override
    public Flux<Request> getAllRequests() {
        return null;
    }

    @Override
    public Mono<Request> getRequestById(String id) {
        return null;
    }

    @Override
    public Mono<Request> save(Request request) {
        return null;
    }

    @Override
    public Request updateByIdRequest(String id, Request request) {
        return null;
    }

    /*        @Override
    Flux<Request> getAllRequests();

    @Override
    Mono<Void> deleteRequestById(String id);

    @Override
    Mono<Request> save(Request request);*/

    /*@Override
    default Optional<Request> updateByIdRequest(String id, Request request){
        return findById(id);
    }*/

    /*
      @Override
    public Request save(Request request) {

        RequestDocument requestDocument = new RequestDocument(request.getId(), request.getPropertyId(), request.getType(), request.getDescription(), stageMapper.toDocumentArray(request.getStages()), request.getUserId(), request.getProviderId(), LocalDateTime.now());
        return new Request(requestDocument.getId(), requestDocument.getPropertyId(), requestDocument.getType(), requestDocument.getDescription(), stageMapper.toModelArray(requestDocument.getStages()), requestDocument.getUserId(), requestDocument.getProviderId(), requestDocument.getCreatedAt());
    }

    @Override
    public List<Request> findAll() {
        return springDataMongoRequestRepository.findAll().stream()
                .map(requestDocument ->new Request(requestDocument.getId(), requestDocument.getPropertyId(), requestDocument.getType(), requestDocument.getDescription(), stageMapper.toModelArray(requestDocument.getStages()), requestDocument.getUserId(), requestDocument.getProviderId(), requestDocument.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Request findById(String id) {

        RequestDocument requestDocument = springDataMongoRequestRepository.findById(id).orElse(null);
        if(requestDocument == null) return null;
        return new Request(requestDocument.getId(), requestDocument.getPropertyId(), requestDocument.getType(), requestDocument.getDescription(), stageMapper.toModelArray(requestDocument.getStages()), requestDocument.getUserId(), requestDocument.getProviderId(), requestDocument.getCreatedAt()
        );
    }
     */
}
