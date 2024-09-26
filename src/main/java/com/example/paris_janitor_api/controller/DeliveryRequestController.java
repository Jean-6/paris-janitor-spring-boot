package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.DeliveryRequest;
import com.example.paris_janitor_api.model.RequestStatus;
import com.example.paris_janitor_api.service.DeliveryRequestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/delivery-request")
public class DeliveryRequestController {

    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Page<DeliveryRequest>>> getDeliveriesRequestPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try{
            Page<DeliveryRequest> deliveryPage= deliveryRequestService.getDeliveriesRequestPerPage(page, size);
            if(deliveryPage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(Optional.of(deliveryPage));
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeliveryRequest>> getDeliveriesRequest() {
        try{
            List<DeliveryRequest> deliveryRequestList = deliveryRequestService.getDeliveriesRequest();
            return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestList);
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<DeliveryRequest>> getDeliveryRequestById(@PathVariable String id) {
        
        if(id.isEmpty() || id.equals("null")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try{
            Optional<DeliveryRequest> deliveryRequest = deliveryRequestService.getDeliveryRequestById(id);
            if(deliveryRequest.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestService.getDeliveryRequestById(id));
        }catch (ResourceNotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeliveryRequest> save(@RequestBody DeliveryRequest deliveryRequest) {
        try{
            DeliveryRequest request = deliveryRequestService.saveDeliveryRequest(deliveryRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(value="/{deliveryRequestId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDeliveryRequest(@PathVariable String deliveryRequestId) {
        try{
            Optional<DeliveryRequest> optionalDeliveryRequest = deliveryRequestService.deleteDeliveryRequest(deliveryRequestId);
            if(optionalDeliveryRequest.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body("Ressource id: " + deliveryRequestId+" supprimee");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ressource introuvable");

        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requête invalide");
        }
    }


    @PatchMapping(value = "/update/{deliveryRequestId}/{status}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>update(@PathVariable String deliveryRequestId, @PathVariable RequestStatus status) {
        try{
            Optional<DeliveryRequest> deliveryRequestUpdated= deliveryRequestService.updateDeliveryRequestStage(deliveryRequestId,status);
            if(deliveryRequestUpdated.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestUpdated);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ressource Id :"+deliveryRequestId+"introuvable");
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requête invalide");
        }
    }
}
