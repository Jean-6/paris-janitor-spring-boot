package com.example.paris_janitor_api.application.port.in.request;

import com.example.paris_janitor_api.core.model.Request;
import reactor.core.publisher.Mono;

public interface DeleteRequestByIdPort {
    Mono<Request> deleteById(String id);
}
