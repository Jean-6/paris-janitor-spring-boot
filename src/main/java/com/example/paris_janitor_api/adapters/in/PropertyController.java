package com.example.paris_janitor_api.adapters.in;


import com.example.paris_janitor_api.application.port.in.property.*;
import com.example.paris_janitor_api.core.model.Property;
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
@RequestMapping("/api/property")
public class PropertyController {

    private final DeletePropertyByIdPort deletePropertyByIdPort;
    private final LoadAllPropertiesPort loadAllPropertiesPort;
    private final LoadPropertyByIdPort loadPropertyByIdPort;
    private final PersistPropertyPort persistPropertyPort;
    private final UpdatePropertyPort updatePropertyPort;

    static Logger logger = LoggerFactory.getLogger(PropertyController.class);

    public PropertyController(DeletePropertyByIdPort deletePropertyByIdPort, LoadAllPropertiesPort loadAllPropertiesPort, LoadPropertyByIdPort loadPropertyByIdPort, PersistPropertyPort persistPropertyPort, UpdatePropertyPort updatePropertyPort) {
        this.deletePropertyByIdPort = deletePropertyByIdPort;
        this.loadAllPropertiesPort = loadAllPropertiesPort;
        this.loadPropertyByIdPort = loadPropertyByIdPort;
        this.persistPropertyPort = persistPropertyPort;
        this.updatePropertyPort = updatePropertyPort;
    }


    @PostMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> save(@RequestBody Property property) {

        return persistPropertyPort.save(property)
                .map(propertySaved -> {
                    logger.info(property.toString());
                    return ResponseEntity.status(HttpStatus.CREATED).body(property);
                })
                .defaultIfEmpty(ResponseEntity.badRequest().build());

    }

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> getById(@RequestParam("id") String id) {

        return loadPropertyByIdPort.getPropertyById(id)
                .map(property -> {
                    logger.info(property.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property);
                })
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }


    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Property>> getProperties1() {
        Flux<Property> properties = loadAllPropertiesPort.getAllProperties()
                .doOnNext(property -> {logger.info(property.toString());})
                .onErrorResume(error -> {
                    logger.error(error.getMessage());
                    return Flux.empty();
                });
        return ResponseEntity.ok().body(properties);
    }


    @PutMapping(value="/{id}",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> update(@PathVariable("id") String id, @RequestBody Property property) {

        return updatePropertyPort.updateProperty(id,property)
                .map(property1 -> {
                    logger.info(property1.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property1);
                }).defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> delete(@PathVariable("id") String id) {
        return deletePropertyByIdPort.deleteById(id)
                .map(property -> {
                    logger.info(property.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property);
                }).defaultIfEmpty(ResponseEntity.noContent().build());
    }

}

