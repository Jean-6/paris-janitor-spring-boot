package com.example.paris_janitor_api.application.port.in.request;

import com.example.paris_janitor_api.core.model.Request;
import reactor.core.publisher.Flux;

public interface LoadAllRequestsPort {
    Flux<Request> getAllRequest();
}
