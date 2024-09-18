package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.DeliveryRequest;
import com.example.paris_janitor_api.repository.DeliveryRequestRepository;
import com.example.paris_janitor_api.service.DeliveryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryRequestServiceImpl implements DeliveryRequestService {

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    @Override
    public Page<DeliveryRequest> getDeliveriesRequest(int page, int size) {
        return null;
    }

    @Override
    public DeliveryRequest saveDeliveryRequest(DeliveryRequest delivery) {
        try {
            return deliveryRequestRepository.save(delivery);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public Optional<DeliveryRequest> getDeliveryRequestById(String deliveryRequestId) {
        return Optional.ofNullable(deliveryRequestRepository.findById(deliveryRequestId)
                .orElseThrow(() -> new ResourceNotFoundException(deliveryRequestId)));
    }

    @Override
    public Iterable<DeliveryRequest> getDeliveriesRequest() {
        try {
            return deliveryRequestRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void deleteDeliveryRequest(String deliveryRequestId) {
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(deliveryRequestId)
                .orElseThrow(() -> new ResourceNotFoundException(deliveryRequestId));
        deliveryRequestRepository.deleteById(deliveryRequest.getId());
    }
}
