package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.model.Delivery;
import com.example.paris_janitor_api.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        return null;
    }

    @Override
    public Optional<Delivery> getDeliveryById(Integer deliveryId) {
        return Optional.empty();
    }

    @Override
    public Iterable<Delivery> getDelivery() {
        return null;
    }

    @Override
    public void deleteDelivery(String deliveryId) {

    }
}
