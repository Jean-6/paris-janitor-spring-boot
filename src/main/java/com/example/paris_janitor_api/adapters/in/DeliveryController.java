package com.example.paris_janitor_api.adapters.in;

import com.example.paris_janitor_api.application.port.in.delivery.*;
import com.example.paris_janitor_api.core.model.Delivery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/delivery")
@Tag(name = "Delivery API", description = "Delivery management")
public class DeliveryController {

    private final DeleteDeliveryByIdPort deleteDeliveryByIdPort;
    private final LoadAllDeliveriesPort loadAllDeliveriesPort;
    private final LoadDeliveryByIdPort loadDeliveryByIdPort;
    private final PersistDeliveryPort persistDeliveryPort;
    private final UpdateDeliveryPort updateDeliveryPort;

    public DeliveryController(DeleteDeliveryByIdPort deleteDeliveryByIdPort, LoadAllDeliveriesPort loadAllDeliveriesPort, LoadDeliveryByIdPort loadDeliveryByIdPort, PersistDeliveryPort persistDeliveryPort, UpdateDeliveryPort updateDeliveryPort) {
        this.deleteDeliveryByIdPort = deleteDeliveryByIdPort;
        this.loadAllDeliveriesPort = loadAllDeliveriesPort;
        this.loadDeliveryByIdPort = loadDeliveryByIdPort;
        this.persistDeliveryPort = persistDeliveryPort;
        this.updateDeliveryPort = updateDeliveryPort;
    }


    @Operation(summary = "Create a new delivery", description = "Save a new delivery in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delivery created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid delivery data",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> save(@RequestBody Delivery delivery) {
        return persistDeliveryPort.saveDelivery(delivery)
                .map(deliverySaved->{
                    log.info("save delivery");
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(deliverySaved);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    log.error("Saving delivery error : "+err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @Operation(summary = "Retrieve a delivery by ID", description = "Get a delivery from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID provided",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Delivery not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> getDeliveryById(@PathVariable("id")  String id) {

        return loadDeliveryByIdPort.getDeliveryById(id)
                .map(delivery -> {
                    log.info("get delivery");
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(delivery);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(error->{
                    log.info("Error get delivery");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @Operation(summary = "Retrieve all deliveries", description = "Get all deliveries stored in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deliveries retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Delivery>> getDeliveries() {
        Flux<Delivery> deliveries = loadAllDeliveriesPort.getAllDeliveries()
                .doOnNext(delivery -> log.info("get delivery"))
                .onErrorResume(err -> {
                    log.error("Error getting data of deliveries or database access", err);
                    return Flux.error(err);
                });

        return ResponseEntity.ok().body(deliveries);
    }

    @Operation(summary = "Update a delivery by ID", description = "Modify an existing delivery in the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid delivery data",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Delivery not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PutMapping(value="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> updateDelivery(@PathVariable("id")  String id, @RequestBody Delivery delivery) {
        return updateDeliveryPort.updateDelivery(id,delivery)
                .map(delivery1 -> {
                    log.info("update delivery");
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(delivery1);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    log.error("Error updating delivery");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @Operation(summary = "Delete a delivery by ID", description = "Remove a delivery from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Delivery not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{deliveryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Delivery>> deleteDelivery(@PathVariable String id) {

        return deleteDeliveryByIdPort.deleteById(id)
                .map(delivery->{
                    log.info("delete delivery");
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(delivery);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    log.error("Error deleting delivery");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}
