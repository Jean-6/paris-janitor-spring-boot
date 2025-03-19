package com.example.paris_janitor_api.adapters.in;

import com.example.paris_janitor_api.application.port.in.delivery.*;
import com.example.paris_janitor_api.core.model.Delivery;
import com.example.paris_janitor_api.infrastructure.exception.GenericException;
import com.example.paris_janitor_api.infrastructure.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeleteDeliveryByIdPort deleteDeliveryByIdPort;
    private final LoadAllDeliveriesPort loadAllDeliveriesPort;
    private final LoadDeliveryByIdPort loadDeliveryByIdPort;
    private final PersistDeliveryPort persistDeliveryPort;
    private final UpdateDeliveryPort updateDeliveryPort;

    static Logger logger = LoggerFactory.getLogger(DeliveryController.class);


    public DeliveryController(DeleteDeliveryByIdPort deleteDeliveryByIdPort, LoadAllDeliveriesPort loadAllDeliveriesPort, LoadDeliveryByIdPort loadDeliveryByIdPort, PersistDeliveryPort persistDeliveryPort, UpdateDeliveryPort updateDeliveryPort) {
        this.deleteDeliveryByIdPort = deleteDeliveryByIdPort;
        this.loadAllDeliveriesPort = loadAllDeliveriesPort;
        this.loadDeliveryByIdPort = loadDeliveryByIdPort;
        this.persistDeliveryPort = persistDeliveryPort;
        this.updateDeliveryPort = updateDeliveryPort;
    }


    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> save(@RequestBody Delivery delivery) {

        try{
            if(delivery.getDescription().isBlank()){
                throw new Exception("Description is blank");
            }
            return persistDeliveryPort.saveDelivery(delivery)
                    .map(savedDelivery -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(savedDelivery))
                    .onErrorResume(err->{
                        log.error("Error saving delivery", err);
                        return Mono.error(new GenericException("Error saving delivery"));
                    });
        }catch (Exception e){
            log.error(e.getMessage());
            return Mono.error(new GenericException("Error saving delivery"));
        }
    }


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> getDeliveryById(@RequestParam("id")  String id) {

        try{
            if(id.isBlank()){
                throw new Exception("DeliveryReqId is blank");
            }
            return loadDeliveryByIdPort.getDeliveryById(id)
                    .map(delivery -> ResponseEntity.ok().body(delivery))
                    .switchIfEmpty(Mono.error(new ResourceNotFoundException("DeliveryReqId not found")))
                    .onErrorResume(err->{
                        log.error("Error getting delivery", err);
                        return Mono.error(new GenericException(err.getMessage()));
                    });
        }catch (Exception e){
            log.error(e.getMessage());
            return Mono.error(new GenericException("Error getting delivery"));
        }
    }



    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ResponseEntity<Delivery>> getDeliveries() {
        return loadAllDeliveriesPort.getAllDeliveries()
                .map(delivery -> ResponseEntity.ok().body(delivery))
                .onErrorResume(err->{
                    log.error("Error getting deliveries", err);
                    return Mono.error(new GenericException(err.getMessage()));
                });
    }

    @DeleteMapping(value = "/{deliveryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  Mono<ResponseEntity<Void>> deleteDelivery(@PathVariable String id) {

        try{
            if(id.isBlank()){
                throw new IllegalArgumentException("id ne peut etre null");
            }
            return deleteDeliveryByIdPort.deleteById(id)
                    .then(Mono.just(ResponseEntity.ok().<Void>build()));
        }catch (Exception e){
            log.error(e.getMessage());
            return Mono.error(new GenericException(e.getMessage()));
        }
    }
}
