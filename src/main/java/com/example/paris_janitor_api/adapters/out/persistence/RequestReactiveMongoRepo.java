package com.example.paris_janitor_api.adapters.out.persistence;


import com.example.paris_janitor_api.core.model.Request;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RequestReactiveMongoRepo extends ReactiveMongoRepository<Request, String> {
}
