package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.DeliveryReq;
import com.example.paris_janitor_api.repository.DeliveryReqRepository;
import com.example.paris_janitor_api.service.DeliveryReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryReqServiceImpl implements DeliveryReqService {

    @Autowired
    private DeliveryReqRepository deliveryReqRepository;

    @Override
    public DeliveryReq saveDeliveryReq(DeliveryReq delivery) {
        try {
            return deliveryReqRepository.save(delivery);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public Optional<DeliveryReq> getDeliveryReqById(String deliveryReqId) {
        return Optional.ofNullable(deliveryReqRepository.findById(deliveryReqId)
                .orElseThrow(() -> new ResourceNotFoundException(deliveryReqId)));
    }

    @Override
    public Iterable<DeliveryReq> getDeliveriesReq() {
        try {
            return deliveryReqRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void deleteDeliveryReq(String deliveryReqId) {
        DeliveryReq delivery = deliveryReqRepository.findById(deliveryReqId)
                .orElseThrow(() -> new ResourceNotFoundException(deliveryReqId));
        deliveryReqRepository.deleteById(delivery.getId());
    }
}
