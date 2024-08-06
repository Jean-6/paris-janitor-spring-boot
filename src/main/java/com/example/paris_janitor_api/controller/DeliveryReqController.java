package com.example.paris_janitor_api.controller;


import com.example.paris_janitor_api.model.Delivery;
import com.example.paris_janitor_api.service.DeliveryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/deliveryReq")
public class DeliveryReqController {
    @Autowired
    private DeliveryService deliveryReqService;

    @PostMapping("/")
    public Delivery save(@RequestBody Delivery delivery) {
        return deliveryReqService.saveDelivery(delivery);
    }

    @GetMapping("/{deliveryId}")
    public Optional<Delivery> getDeliveryById(@PathVariable Integer deliveryId) {
        return deliveryReqService.getDeliveryById(deliveryId);
    }

    @GetMapping("/")
    public Iterable<Delivery> getDelivery(){
        return deliveryReqService.getDelivery();
    }

    @DeleteMapping("/{deliveryId}")
    public  void deleteDelivery(@PathVariable String deliveryId) {
        deliveryReqService.deleteDelivery(deliveryId);
    }
}
