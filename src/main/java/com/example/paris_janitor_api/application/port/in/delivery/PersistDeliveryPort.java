package com.example.paris_janitor_api.application.port.in.delivery;

import com.example.paris_janitor_api.core.model.Delivery;

public interface PersistDeliveryPort {
    Delivery saveDelivery(Delivery delivery) ;
}
