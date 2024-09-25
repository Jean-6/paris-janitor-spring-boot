package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.Delivery;
import com.example.paris_janitor_api.repository.DeliveryRepository;
import com.example.paris_janitor_api.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;


    public Delivery saveDelivery(Delivery delivery) {
        try {
            return deliveryRepository.save(delivery);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public Optional<Delivery> getDeliveryById(String deliveryId) {
        return  Optional.ofNullable(deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException(deliveryId)));
    }

    @Override
    public Iterable<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }

    public  void deleteDelivery(final String id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        deliveryRepository.deleteById(delivery.getId());
    }
}
