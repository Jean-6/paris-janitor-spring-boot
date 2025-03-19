package com.example.paris_janitor_api.application.port.out.request;

import com.example.paris_janitor_api.core.model.Request;
import reactor.core.publisher.Mono;

public interface UpdateRequestPort {
    Mono<Request> findByIdAndUpdate(String id, Request request);
}