package com.example.paris_janitor_api.application.port.out.request;

import com.example.paris_janitor_api.core.model.Request;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface LoadRequestByIdPort {
    Mono<Request> findById(String id);
}
