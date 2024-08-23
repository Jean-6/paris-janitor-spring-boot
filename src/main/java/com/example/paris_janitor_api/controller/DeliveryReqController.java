package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.model.DeliveryReq;
import com.example.paris_janitor_api.service.DeliveryReqService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/deliveryReq")
public class DeliveryReqController {

    @Autowired
    private DeliveryReqService deliveryReqService;

    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<DeliveryReq>> getDeliveriesReq() {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryReqService.getDeliveriesReq());
    }

    @GetMapping(value="/{deliveryReqId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<DeliveryReq>> getDeliveryReqById(@PathVariable String deliveryReqId) {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryReqService.getDeliveryReqById(deliveryReqId));
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeliveryReq> save(@RequestBody DeliveryReq deliveryReq) {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryReqService.saveDeliveryReq(deliveryReq));
    }

    @DeleteMapping(value="/{deliveryReqId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDeliveryReq(@PathVariable String deliveryReqId) {
        try{
            deliveryReqService.deleteDeliveryReq(deliveryReqId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
