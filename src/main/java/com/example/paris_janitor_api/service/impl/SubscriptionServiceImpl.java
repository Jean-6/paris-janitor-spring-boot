package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.Subscription;
import com.example.paris_janitor_api.repository.SubscriptionRepository;
import com.example.paris_janitor_api.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Iterable<Subscription> findAll() {
        return null;
    }

    @Override
    public Optional<Subscription> findById(String id) {
        return Optional.ofNullable(subscriptionRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
    }

    @Override
    public Subscription save(Subscription subscription) {
        try {
            return subscriptionRepository.save(subscription);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public void deleteById(String id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        subscriptionRepository.deleteById(subscription.getId());
    }
}
