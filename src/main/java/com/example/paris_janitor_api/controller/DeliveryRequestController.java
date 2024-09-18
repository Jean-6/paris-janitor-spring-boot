package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.model.DeliveryRequest;
import com.example.paris_janitor_api.service.DeliveryRequestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/deliveryRequest")
public class DeliveryRequestController {

    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Page<DeliveryRequest>>> getDeliveriesRequestPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return  ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(deliveryRequestService.getDeliveriesRequest(page, size)));
    }

    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<DeliveryRequest>> getDeliveriesRequest() {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestService.getDeliveriesRequest());
    }

    @GetMapping(value="/{deliveryRequestId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<DeliveryRequest>> getDeliveryRequestById(@PathVariable String deliveryRequestId) {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestService.getDeliveryRequestById(deliveryRequestId));
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeliveryRequest> save(@RequestBody DeliveryRequest deliveryRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestService.saveDeliveryRequest(deliveryRequest));
    }

    @DeleteMapping(value="/{deliveryRequestId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDeliveryRequest(@PathVariable String deliveryRequestId) {
        try{
            deliveryRequestService.deleteDeliveryRequest(deliveryRequestId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
