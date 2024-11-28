package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.Request;
import com.example.paris_janitor_api.model.RequestStatus;
import com.example.paris_janitor_api.service.DeliveryRequestService;
import com.mongodb.client.result.UpdateResult;
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
public class RequestController {

    @Autowired
    private DeliveryRequestService deliveryRequestService;



    @PostMapping(value="/assign",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> assignRequest(@RequestParam("providerId") String providerId, @RequestParam("requestId") String requestId) {

        if(providerId.isEmpty() || requestId.isEmpty()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try{
            UpdateResult result = deliveryRequestService.assignRequest(providerId,requestId);
            if(result.getModifiedCount()>0){
                return ResponseEntity.status(HttpStatus.OK).body("Provider affected");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("delivery or provider not found");
            }
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Page<Request>>> getDeliveriesRequestPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try{
            Page<Request> deliveryPage= deliveryRequestService.getDeliveriesRequestPerPage(page, size);
            if(deliveryPage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(Optional.of(deliveryPage));
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Request>> getDeliveriesRequest() {
        try{
            List<Request> requestList = deliveryRequestService.getDeliveriesRequest();
            return ResponseEntity.status(HttpStatus.OK).body(requestList);
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value="/byPropertyId/{propertyIds}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDeliveriesRequestByPropertyId(@PathVariable List<String> propertyIds) {

        if(propertyIds.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
        try{
            List<Request> requestList = deliveryRequestService.getDeliveryRequestByPropertyIds(propertyIds);
            return ResponseEntity.status(HttpStatus.OK).body(requestList);
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value="/user",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Request>> getDeliveriesRequestBy(@RequestParam("userId") String userId) {
        try{
            List<Request> requestList = deliveryRequestService.getDeliveryRequestBy(userId);
            return ResponseEntity.status(HttpStatus.OK).body(requestList);
        }catch (ResourceNotFoundException Ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Request>> getDeliveryRequestById(@PathVariable String id) {
        
        if(id.isEmpty() || id.equals("null")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try{
            Optional<Request> deliveryRequest = deliveryRequestService.getDeliveryRequestById(id);
            if(deliveryRequest.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestService.getDeliveryRequestById(id));
        }catch (ResourceNotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Request> save(@RequestBody Request deliveryRequest) {
        try{
            Request request = deliveryRequestService.saveDeliveryRequest(deliveryRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(value="/{deliveryRequestId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDeliveryRequest(@PathVariable String deliveryRequestId) {
        try{
            Optional<Request> optionalDeliveryRequest = deliveryRequestService.deleteDeliveryRequest(deliveryRequestId);
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
            Optional<Request> deliveryRequestUpdated= deliveryRequestService.updateDeliveryRequestStage(deliveryRequestId,status);
            if(deliveryRequestUpdated.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(deliveryRequestUpdated);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ressource Id :"+deliveryRequestId+"introuvable");
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requête invalide");
        }
    }
}
