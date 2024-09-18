package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.model.Property;
import com.example.paris_janitor_api.service.PropertyService;
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
@RequestMapping("/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Page<Property>>> getPropertiesPerPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return  ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(propertyService.getProperties(page, size)));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Property>> getPropertyById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPropertyById(id));
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Property> save(@RequestBody Property property) {
        return  ResponseEntity.status(HttpStatus.OK).body(propertyService.saveProperty(property));
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Property>> getProperties() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.getProperties());
    }

    @GetMapping(value="/owner/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Property>> getMyProperties(@PathVariable("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.getMyProperties(userId));
    }

    @DeleteMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> deleteProperty(@PathVariable String propertyId) {
        try{
            propertyService.deleteProperty(propertyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
