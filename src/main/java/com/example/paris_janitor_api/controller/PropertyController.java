package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.dto.PropertyDto;
import com.example.paris_janitor_api.exception.ResourceNotFound;
import com.example.paris_janitor_api.model.Property;
import com.example.paris_janitor_api.service.PropertyService;
import com.example.paris_janitor_api.service.S3Service;
import com.example.paris_janitor_api.util.Response;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/property")
public class PropertyController {

    //@Autowired
    //private PropertyService propertyService;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private ModelMapper modelMapper;


   /* @PostMapping("/")
    public ResponseEntity<Property> save(@RequestBody PropertyDto propertyDto) {
        Property property = propertyService.saveProperty(propertyDto);
        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(property);
    }*/

    /*@PutMapping("/{propertyId}/images")
    public ResponseEntity<?> uploadPropertyImages(@PathVariable String id, @RequestParam("images") List<MultipartFile> imgs){
        Optional<Property> property  = propertyService.getPropertyById(id); //.orElseThrow( () -> new ResourceNotFound("Property",id));
        if(!property.isPresent()){
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Property with "+id.toString()+"not found");
        }
        Property p = propertyService.addImg(imgs,property.get());

        return null;

    }*/

   /* @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable String propertyId) {

        Optional<Property> property = propertyService.getPropertyById(propertyId);
        if(property.isPresent()){
            return ResponseEntity.status(HttpStatus.SC_OK).body(property.get());
        }
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(new Property());
        //return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Property>> getProperties(){
        List<Property> properties = propertyService.getProperties();
        return ResponseEntity.status(HttpStatus.SC_OK).body(properties);
    }

    @DeleteMapping("/{propertyId}")
    public  void deleteProperty(@PathVariable String propertyId) {
        propertyService.deleteProperty(propertyId);
    }*/
}
