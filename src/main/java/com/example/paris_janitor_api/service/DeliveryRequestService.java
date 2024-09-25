package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.DeliveryRequest;
import com.example.paris_janitor_api.model.RequestStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
public interface DeliveryRequestService {

    Page<DeliveryRequest> getDeliveriesRequestPerPage(int page, int size);
    DeliveryRequest saveDeliveryRequest(DeliveryRequest deliveryRequest);
    Optional<DeliveryRequest> getDeliveryRequestById(String id);
    List<DeliveryRequest> getDeliveriesRequest();
    void deleteDeliveryRequest(String id);
    Optional<DeliveryRequest> updateDeliveryRequestStage(String id, RequestStatus status);
}
