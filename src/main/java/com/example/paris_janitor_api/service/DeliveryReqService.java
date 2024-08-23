package com.example.paris_janitor_api.service;
import com.example.paris_janitor_api.model.DeliveryReq;
import java.util.Optional;
public interface DeliveryReqService {
    DeliveryReq saveDeliveryReq(DeliveryReq deliveryreq);
    Optional<DeliveryReq> getDeliveryReqById(String deliveryReqId);
    Iterable<DeliveryReq> getDeliveriesReq();
    void deleteDeliveryReq(String deliveryReqId);
}
