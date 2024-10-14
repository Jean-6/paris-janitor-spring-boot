package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.DeliveryRequest;
import com.example.paris_janitor_api.model.RequestStatus;
import com.example.paris_janitor_api.model.Stage;
import com.example.paris_janitor_api.model.User;
import com.example.paris_janitor_api.repository.DeliveryRequestRepository;
import com.example.paris_janitor_api.repository.PropertyRepository;
import com.example.paris_janitor_api.repository.UserRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryRequestServiceImpl implements DeliveryRequestService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private PropertyRepository propertyRepository;
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

    @Override
    public Optional<List<DeliveryRequest>> getDeliveryRequestByPropertyId(String propId) {
        boolean isExist = this.propertyRepository.existsPropertyById(propId);
        if(isExist){
            Query query = new Query();
            Criteria criteria = Criteria.where("propertyId").is(propId);
            query.addCriteria(criteria);
            return Optional.of(mongoTemplate.find(query, DeliveryRequest.class));
        }
        return Optional.empty();
    }

    @Override
    public List<DeliveryRequest> getDeliveryRequestByPropertyIds(List<String> propIds) {
        Query query = new Query();
        Criteria criteria = Criteria.where("propertyId").in(propIds);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,DeliveryRequest.class);
    }

    @Override
    public List<DeliveryRequest> getDeliveryRequestBy(String userId) {

        Optional<User> optionalUser = this.userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            Query query = new Query();
            Criteria criteria = Criteria.where("userId").is(optionalUser.get().getId());
            query.addCriteria(criteria);
            return mongoTemplate.find(query, DeliveryRequest.class);
        }else {
            throw new ResourceNotFoundException("user does not exist");
        }
    }


}
