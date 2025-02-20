package com.example.paris_janitor_api.core.repository;


import com.example.paris_janitor_api.core.model.Delivery;

import java.util.List;

public interface DeliveryRepository {

    Delivery save(Delivery delivery);
    List<Delivery> findAll() ;
    Delivery findById(String id);
}
