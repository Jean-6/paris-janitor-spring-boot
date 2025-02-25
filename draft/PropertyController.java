package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.service.PropertyService;
import com.example.paris_janitor_api.service.AmazonService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    //@Autowired
    //private AmazonService s3Service;
    @Autowired
    private ModelMapper modelMapper;

    //Get owns properties

    /*@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Property> getMyProperties(@PathVariable String userId) {

        Optional<Property> property = propertyService.getPropertyById(userId);
        if(property.isPresent()){
            return ResponseEntity.status(HttpStatus.SC_OK).body(property.get());
        }
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
    }


    @PostMapping(value = "/",consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Property> save(@RequestBody PropertyDto propertyDto) {
        Property property = propertyService.saveProperty(propertyDto);
        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(property);
    }


    @GetMapping(value = "/{propertyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Property> getPropertyById(@PathVariable String propertyId) {

        Optional<Property> property = propertyService.getPropertyById(propertyId);
        if(property.isPresent()){
            return ResponseEntity.status(HttpStatus.SC_OK).body(property.get());
        }
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Property>> getProperties(){
        List<Property> properties = propertyService.getProperties();
        return ResponseEntity.status(HttpStatus.SC_OK).body(properties);
    }

    @DeleteMapping(value = "/{propertyId}")
    public  void deleteProperty(@PathVariable String propertyId) {
        propertyService.deleteProperty(propertyId);
    }*/
}
