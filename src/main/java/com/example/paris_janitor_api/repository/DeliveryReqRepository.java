package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.DeliveryReq;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryReqRepository extends MongoRepository<DeliveryReq,String> {
}
