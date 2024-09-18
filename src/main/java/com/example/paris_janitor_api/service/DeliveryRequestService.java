package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.DeliveryRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;
public interface DeliveryRequestService {

    Page<DeliveryRequest> getDeliveriesRequest(int page, int size);
    DeliveryRequest saveDeliveryRequest(DeliveryRequest deliveryRequest);
    Optional<DeliveryRequest> getDeliveryRequestById(String deliveryRequestId);
    Iterable<DeliveryRequest> getDeliveriesRequest();
    void deleteDeliveryRequest(String deliveryRequestId);
}
