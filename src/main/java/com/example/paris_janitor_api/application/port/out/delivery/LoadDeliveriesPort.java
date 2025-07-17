package com.example.paris_janitor_api.application.port.out.delivery;

import com.example.paris_janitor_api.core.model.Delivery;

import java.util.List;

public interface LoadDeliveriesPort {
    List<Delivery> findAll();
}
