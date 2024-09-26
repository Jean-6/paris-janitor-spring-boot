package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.DeliveryRequest;
import com.example.paris_janitor_api.model.RequestStatus;
import com.example.paris_janitor_api.model.Stage;
import com.example.paris_janitor_api.repository.DeliveryRequestRepository;
import com.example.paris_janitor_api.service.DeliveryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRequestServiceImpl implements DeliveryRequestService {

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<DeliveryRequest> getDeliveriesRequestPerPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return deliveryRequestRepository.findAll(pageable);
    }

    @Override
    public DeliveryRequest saveDeliveryRequest(DeliveryRequest delivery) {
        Stage s =new Stage();
        s.setStatus(RequestStatus.PENDING);
        s.setCreatedAt(LocalDateTime.now());
        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(s);
        delivery.setStage(stages);
        return deliveryRequestRepository.save(delivery);
    }

    @Override
    public Optional<DeliveryRequest> getDeliveryRequestById(String id) {
        return deliveryRequestRepository.findById(id);
    }

    @Override
    public List<DeliveryRequest> getDeliveriesRequest() {
        return deliveryRequestRepository.findAll();
    }

    @Override
    public Optional<DeliveryRequest> deleteDeliveryRequest(String id) {
        Optional<DeliveryRequest> optionalDeliveryRequest = deliveryRequestRepository.findById(id);
        if(optionalDeliveryRequest.isEmpty()){
            return Optional.empty();
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(optionalDeliveryRequest.get().getId()));
        DeliveryRequest deliveryRequest=mongoTemplate.findAndRemove(query, DeliveryRequest.class);
        return Optional.ofNullable(deliveryRequest);
    }

    @Override
    public Optional<DeliveryRequest> updateDeliveryRequestStage(String deliveryRequestId,RequestStatus status) {

        Optional<DeliveryRequest> deliveryRequest = deliveryRequestRepository.findById(deliveryRequestId);
        if(deliveryRequest.isPresent()){
            Stage s = new Stage();
            s.setStatus(status);
            s.setCreatedAt(LocalDateTime.now());
            deliveryRequest.get().getStage().add(s);

            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(deliveryRequestId));

            Update update = new Update().addToSet("stage",s);
            mongoTemplate.updateFirst(query,update, DeliveryRequest.class);
            return deliveryRequest;
        }
        return Optional.empty();
    }
}
