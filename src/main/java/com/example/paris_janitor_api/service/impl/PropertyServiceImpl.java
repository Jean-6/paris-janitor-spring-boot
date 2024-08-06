package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.dto.ImageDto;
import com.example.paris_janitor_api.dto.PropertyDto;
import com.example.paris_janitor_api.exception.ResourceNotFound;
import com.example.paris_janitor_api.model.Image;
import com.example.paris_janitor_api.model.Property;
//import com.example.paris_janitor_api.repository.PropertyRepository;
import com.example.paris_janitor_api.service.PropertyService;
import com.example.paris_janitor_api.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PropertyServiceImpl implements PropertyService {

   // @Autowired
   // private PropertyRepository propertyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private S3Service s3Service;
/*
    @Override
    public Property saveProperty(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto,Property.class);
        return propertyRepository.save(property);
    }

    @Override
    public Property addImg(List<MultipartFile> imgs, Property property) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        imgs.forEach(img -> {
            try {
                File file = s3Service.convertMultipartToFile(img);
                String filePath = property.getId() +"/"+img.getOriginalFilename();
                String url = s3Service.uploadFile((MultipartFile) file,filePath);
                property.getImages().add(String.valueOf(new Image("",url,filePath)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return null;
    }

    @Override
    public Optional<Property> getPropertyById(String propertyId) {
        return Optional.ofNullable(propertyRepository.findPropertiesById(propertyId));
    }

    @Override
    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public void deleteProperty(String propertyId) {
        propertyRepository.deleteById(propertyId);
    }*/
}
