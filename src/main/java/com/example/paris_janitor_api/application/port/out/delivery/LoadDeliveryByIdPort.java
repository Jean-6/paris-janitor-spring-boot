package com.example.paris_janitor_api.application.port.out.delivery;


import com.example.paris_janitor_api.core.model.Delivery;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface LoadDeliveryByIdPort {
  Mono<Delivery> findById(String id);
}
