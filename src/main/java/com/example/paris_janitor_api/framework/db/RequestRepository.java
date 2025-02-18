package com.example.paris_janitor_api.framework.db;

import com.example.paris_janitor_api.framework.entity.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository extends MongoRepository<Request,String> {
}
