package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.Delivery;
import com.example.paris_janitor_api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface DeliveryRepository extends MongoRepository<Delivery,String> {

    List<Delivery> findAll();
    User findDeliveryById(String id);

    @Override
    <S extends Delivery> S insert(S entity);

    @Override
    <S extends Delivery> S save(S entity);

    @Override
    void deleteById(String id);

    //Delivery updateDeliveryById(String id);
}
