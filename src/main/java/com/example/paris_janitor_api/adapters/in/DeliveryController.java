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
        return persistDeliveryPort.saveDelivery(delivery)
                .map(deliverySaved->{
                    logger.info("save delivery");
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(deliverySaved);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    logger.error("Saving delivery error : "+err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });

    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> getDeliveryById(@PathVariable("id")  String id) {

        return loadDeliveryByIdPort.getDeliveryById(id)
                .map(delivery -> {
                    logger.info("get delivery");
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(delivery);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error->{
                    logger.info("Error get delivery");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Delivery>> getDeliveries() {
        Flux<Delivery> deliveries = loadAllDeliveriesPort.getAllDeliveries()
                .doOnNext(delivery -> logger.info("get delivery"))
                .onErrorResume(err -> {
                    log.error("Error getting data of deliveries or database access", err);
                    return Flux.error(err);
                });

        return ResponseEntity.ok().body(deliveries);
    }

    @PutMapping(value="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> updateDelivery(@PathVariable("id")  String id, @RequestBody Delivery delivery) {
        return updateDeliveryPort.updateDelivery(id,delivery)
                .map(delivery1 -> {
                    logger.info("update delivery");
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(delivery1);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    logger.error("Error updating delivery");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }


    @DeleteMapping(value = "/{deliveryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> deleteDelivery(@PathVariable String id) {

        return deleteDeliveryByIdPort.deleteById(id)
                .map(delivery->{
                    logger.info("delete delivery");
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(delivery);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    logger.error("Error deleting delivery");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}
