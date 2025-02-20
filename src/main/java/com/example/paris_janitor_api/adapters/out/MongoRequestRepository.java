package com.example.paris_janitor_api.adapters.out;

import com.example.paris_janitor_api.core.mapper.StageMapper;
import com.example.paris_janitor_api.core.model.Request;
import com.example.paris_janitor_api.core.repository.RequestRepository;
import com.example.paris_janitor_api.framework.db.RequestDocument;
import com.example.paris_janitor_api.framework.repository.SpringDataMongoRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class MongoRequestRepository implements RequestRepository {

    private final SpringDataMongoRequestRepository springDataMongoRequestRepository;
    private final StageMapper stageMapper;

    @Autowired
    public MongoRequestRepository(SpringDataMongoRequestRepository springDataMongoRequestRepository, StageMapper stageMapper) {
        this.springDataMongoRequestRepository = springDataMongoRequestRepository;
        this.stageMapper = stageMapper;
    }

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
}
