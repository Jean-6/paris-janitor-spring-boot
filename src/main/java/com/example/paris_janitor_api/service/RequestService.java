package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.Request;
import com.example.paris_janitor_api.model.RequestStatus;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
public interface RequestService {
    Page<Request> getDeliveriesRequestPerPage(int page, int size);
    Request saveDeliveryRequest(Request request);
    Optional<Request> getDeliveryRequestById(String id);
    List<Request> getDeliveriesRequest();
    Optional<Request> deleteDeliveryRequest(String id);
    Optional<Request> updateDeliveryRequestStage(String id, RequestStatus status);
    Optional<List<Request>> getDeliveryRequestByPropertyId(String propId);
    List<Request> getDeliveryRequestByPropertyIds(List<String> propIds);
    List<Request> getDeliveryRequestBy(String userId);
    UpdateResult assignRequest(String providerId, String deliveryRequestId);
}
