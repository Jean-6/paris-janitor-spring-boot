package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.model.Delivery;
import com.example.paris_janitor_api.service.DeliveryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;


    @GetMapping(value = "/{deliveryReqId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Delivery>>getDeliveryById(@PathVariable String deliveryReqId) {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.getDeliveryById(deliveryReqId));
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Delivery> save(@RequestBody Delivery delivery) {
        return  ResponseEntity.status(HttpStatus.OK).body(deliveryService.saveDelivery(delivery));
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Delivery>> getDeliveries() {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.getDeliveries());
    }

    @DeleteMapping(value = "/{deliveryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> deleteDelivery(@PathVariable String deliveryId) {

        try{
            deliveryService.deleteDelivery(deliveryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }

}
