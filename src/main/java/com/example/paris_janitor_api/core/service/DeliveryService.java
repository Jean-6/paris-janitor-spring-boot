package com.example.paris_janitor_api.core.service;


import com.example.paris_janitor_api.core.model.Delivery;
import java.util.List;

public interface DeliveryService  {


        Delivery createDelivery(Delivery delivery);
        List<Delivery> getAllDeliveries();
        Delivery getDeliveryById(String id);


}
