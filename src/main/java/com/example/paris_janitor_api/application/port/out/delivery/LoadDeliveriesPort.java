package com.example.paris_janitor_api.application.port.out.delivery;

import com.example.paris_janitor_api.core.model.Delivery;
import reactor.core.publisher.Flux;

public interface LoadDeliveriesPort {
    Flux<Delivery> findAll();
}
