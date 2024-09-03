package com.example.paris_janitor_api.repository;

import com.example.paris_janitor_api.model.Image;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

    @Override
    List<Image> findAllById(Iterable<String> strings);
}
