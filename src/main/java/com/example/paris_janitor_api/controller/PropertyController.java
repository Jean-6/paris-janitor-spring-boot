package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.exception.BadRequestException;
import com.example.paris_janitor_api.exception.ResourceNotFoundException;
import com.example.paris_janitor_api.model.Property;
import com.example.paris_janitor_api.service.PropertyService;
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
@RequestMapping("/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Page<Property>>> getPropertiesPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        try{
            Page<Property> propertyPage=propertyService.getProperties(page,size);
            if(propertyPage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(Optional.of(propertyPage));
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Property>> getPropertyById(@PathVariable String id) {

        if(id.isEmpty() || id.equals("null")){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
        }
        try{
            Optional<Property> property = propertyService.getPropertyById(id);
            if(property.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Optional.empty());
            }
            return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPropertyById(id));
        }catch(ResourceNotFoundException notFoundEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Property>> getPropByUserId(@RequestParam String userId) {

        if(userId.isEmpty() || userId.equals("null")){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try{
            List<Property> property = propertyService.getPropertyByUserId(userId);
            if(property.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPropertyByUserId(userId));
        }catch(ResourceNotFoundException notFoundEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Property> save(@RequestBody Property property) {
        try{
            Property propertyCreated= propertyService.saveProperty(property);
            return ResponseEntity.status(HttpStatus.CREATED).body(propertyCreated);
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Property>> getProperties() {
        try{
            List<Property> propertyList=propertyService.getProperties();
            if(propertyList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(propertyList);
            }
            return ResponseEntity.status(HttpStatus.OK).body(propertyList);
        }catch(BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Property> deleteProperty(@PathVariable String propertyId) {
        try{
            Property property = propertyService.deleteProperty(propertyId);
            if(property==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(property);
        }catch (BadRequestException badEx){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (RuntimeException runtimeEx){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
