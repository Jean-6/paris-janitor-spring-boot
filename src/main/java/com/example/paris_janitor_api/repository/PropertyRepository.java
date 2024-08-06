package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.Property;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PropertyRepository extends MongoRepository<Property,String> {

  /*  List<Property> findAll();
    Property findPropertiesById(String id);*/
/*
    @Override
    <S extends Property> S insert(S entity);

    @Override
    <S extends Property> S save(S entity);
*/
    /*@Override
    void deleteById(String id);
*/
    //Property updatePropertyById(String id);
}
