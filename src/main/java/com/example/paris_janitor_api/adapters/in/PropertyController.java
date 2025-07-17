package com.example.paris_janitor_api.adapters.in;
import com.example.paris_janitor_api.application.port.in.property.*;
import com.example.paris_janitor_api.core.model.Property;
import com.example.paris_janitor_api.infrastructure.web.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


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


    public PropertyController(DeletePropertyByIdPort deletePropertyByIdPort,
                              LoadAllPropertiesPort loadAllPropertiesPort,
                              LoadPropertyByIdPort loadPropertyByIdPort,
                              PersistPropertyPort persistPropertyPort,
                              UpdatePropertyPort updatePropertyPort) {
        this.deletePropertyByIdPort = deletePropertyByIdPort;
        this.loadAllPropertiesPort = loadAllPropertiesPort;
        this.loadPropertyByIdPort = loadPropertyByIdPort;
        this.persistPropertyPort = persistPropertyPort;
        this.updatePropertyPort = updatePropertyPort;
    }


    @Operation(summary = "Create a new property", description = "Save a new property along with images and documents in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Property created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid property data or file upload error",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value="/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Property>> save(@RequestPart("information") Property property, HttpServletRequest request) {
        log.debug("save method");

        Property propertySaved = persistPropertyPort.save(property);
        return ResponseEntity.ok(
                ResponseWrapper.ok("Property saved",request.getRequestURI(),propertySaved));

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
    @GetMapping(value="/getBy/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Property>> getById(@PathVariable String id) {

        return null;

        /*if(id.isBlank()){
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
                });*/
    }


    @Operation(summary = "Retrieve all properties", description = "Get all properties stored in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Properties retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Property>> getProperties() {

        return null;

       /* Flux<Property> properties = loadAllPropertiesPort.getAllProperties()
                .doOnNext(property -> {log.info(property.toString());})
                .onErrorResume(error -> {
                    log.error(error.getMessage());
                    return Flux.empty();
                });
        return ResponseEntity.ok().body(properties);*/
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
    public ResponseEntity<ResponseWrapper<Property>> update(@PathVariable("id") String id, @RequestBody Property property) {


        return  null;
        /*return updatePropertyPort.updateProperty(id,property)
                .map(property1 -> {
                    log.info(property1.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property1);
                }).defaultIfEmpty(ResponseEntity.badRequest().build())
                .onErrorResume(err->{
                    log.error(err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });*/
    }

    @Operation(summary = "Delete a property by ID", description = "Remove a property from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property deleted successfully"),
            @ApiResponse(responseCode = "204", description = "No content, property was not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Property>> delete(@PathVariable("id") String id) {

        return null;

        /*return deletePropertyByIdPort.deleteById(id)
                .map(property -> {
                    log.info(property.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(property);
                }).defaultIfEmpty(ResponseEntity.noContent().build())
                .onErrorResume(err->{
                    log.error(err.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });*/
    }

}

