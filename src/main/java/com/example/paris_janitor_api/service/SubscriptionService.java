package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.model.Subscription;

import java.util.Optional;

public interface SubscriptionService {
    Iterable<Subscription> findAll() ;
    Optional<Subscription> findById(String id);
    Subscription save(Subscription subscription);
    void deleteById(String id);
}
