package com.example.paris_janitor_api.adapters.in;
import com.example.paris_janitor_api.application.port.in.property.*;
import com.example.paris_janitor_api.core.model.Property;
import com.example.paris_janitor_api.infrastructure.exception.IllegalArgumentException;
import com.example.paris_janitor_api.infrastructure.http.HttpPropertyAttachmentSender;
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
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping("/api/property")
@Tag(name = "Property API", description = "Property management")
public class PropertyController {

    private final DeletePropertyByIdPort deletePropertyByIdPort;
    private final LoadAllPropertiesPort loadAllPropertiesPort;
    private final LoadPropertyByIdPort loadPropertyByIdPort;
    private final PersistPropertyPort persistPropertyPort;
    private final UpdatePropertyPort updatePropertyPort;
    private final HttpPropertyAttachmentSender  httpPropertyAttachmentSender;


    public PropertyController(DeletePropertyByIdPort deletePropertyByIdPort,
                              LoadAllPropertiesPort loadAllPropertiesPort,
                              LoadPropertyByIdPort loadPropertyByIdPort,
                              PersistPropertyPort persistPropertyPort,
                              UpdatePropertyPort updatePropertyPort,
                              HttpPropertyAttachmentSender httpPropertyAttachmentSender1) {
        this.deletePropertyByIdPort = deletePropertyByIdPort;
        this.loadAllPropertiesPort = loadAllPropertiesPort;
        this.loadPropertyByIdPort = loadPropertyByIdPort;
        this.persistPropertyPort = persistPropertyPort;
        this.updatePropertyPort = updatePropertyPort;
        this.httpPropertyAttachmentSender = httpPropertyAttachmentSender1;
    }


    @Operation(summary = "Create a new property", description = "Save a new property along with images and documents in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Property created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid property data or file upload error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value="/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> save(@RequestPart("information") Mono<Property> info,
                                               @RequestPart("pictures") Flux<FilePart> pictures,
                                               @RequestPart("documents") Flux<FilePart> documents) {
        log.debug("save method");
        return info.flatMap(dto ->
                persistPropertyPort.save(dto)
                        .flatMap(savedProperty ->
                                // Envoie des fichiers en parallèle puis retourne la propriété sauvegardée
                                Mono.when(
                                        httpPropertyAttachmentSender.sendPictures(savedProperty.getId(), pictures)
                                                .onErrorResume(err->{
                                                    log.error("Erreur lors de l'envoi des images", err);
                                                    return Mono.error(err);
                                                }),
                                        httpPropertyAttachmentSender.sendDocuments(savedProperty.getId(), documents)
                                                .onErrorResume(err->{
                                                    log.error("Erreur lors de l'envoi des docs", err);
                                                    return Mono.error(err);
                                                })
                                ).thenReturn(savedProperty)
                        )
        ).map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve a property by ID", description = "Get a property from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID provided",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Property not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value="getBy",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> getById(@RequestParam("id") String id) {

        if(id.isBlank()){
            throw new IllegalArgumentException("Property with ID "+ id +" not found");
        }
        return loadPropertyByIdPort.getPropertyById(id)
                .map(property -> {
                    log.info(property.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(err->{
                    log.error(err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }


    @Operation(summary = "Retrieve all properties", description = "Get all properties stored in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Properties retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Property>> getProperties() {
        Flux<Property> properties = loadAllPropertiesPort.getAllProperties()
                .doOnNext(property -> {log.info(property.toString());})
                .onErrorResume(error -> {
                    log.error(error.getMessage());
                    return Flux.empty();
                });
        return ResponseEntity.ok().body(properties);
    }


    @Operation(summary = "Update a property by ID", description = "Modify an existing property in the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid property data",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Property not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value="/{id}",consumes= MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> update(@PathVariable("id") String id, @RequestBody Property property) {

        return updatePropertyPort.updateProperty(id,property)
                .map(property1 -> {
                    log.info(property1.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property1);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    log.error(err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @Operation(summary = "Delete a property by ID", description = "Remove a property from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property deleted successfully"),
            @ApiResponse(responseCode = "204", description = "No content, property was not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Property>> delete(@PathVariable("id") String id) {
        return deletePropertyByIdPort.deleteById(id)
                .map(property -> {
                    log.info(property.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property);
                }).defaultIfEmpty(ResponseEntity.noContent().build())
                .onErrorResume(err->{
                    log.error(err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

}

