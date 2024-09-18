package com.example.paris_janitor_api.controller;


import com.amazonaws.services.s3.AmazonS3;
import com.example.paris_janitor_api.exception.FileDownloadException;
import com.example.paris_janitor_api.exception.FileEmptyException;
import com.example.paris_janitor_api.exception.FileUploadException;
import com.example.paris_janitor_api.model.Image;
import com.example.paris_janitor_api.service.ImageService;
import com.example.paris_janitor_api.service.PropertyService;
import com.example.paris_janitor_api.util.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private AmazonS3 amazonS3;


    @PostMapping(value="/upload/{propId}",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartFile,@PathVariable String propId) throws FileEmptyException, FileUploadException, IOException {

        log.debug("propId : "+propId);
        boolean isPropertyExists = propertyService.existsPropertyById(propId);
        if(!isPropertyExists){
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .message("ID property :"+propId+" does not exist")
                    .isSuccess(false)
                    .status(404)
                    .build();
            return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
        }

        if(multipartFile.isEmpty()){
            throw new FileEmptyException("File is empty. Cannot save an empty file");//Or ResponseEntity No Content
        }
        boolean isValidFile = imageService.isValidFile(multipartFile);
        List<String> allowedExtensions= new ArrayList<>(Arrays.asList("png", "jpg", "jpeg"));
        if(isValidFile && allowedExtensions.contains(imageService.getFileExtension(multipartFile))){
            Image img = imageService.uploadFile(multipartFile,propId);
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .message("file uploaded successfully. File unique name =>" + img.getFileType())
                    .isSuccess(true)
                    .status(200)
                    .data(img.getId())
                    .build();
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        }else{
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .message("Invalid File. File extension or File name is not supported")
                    .isSuccess(false)
                    .status(400)
                    .build();
            return new ResponseEntity<>(genericResponse,HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value="/download",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> downloadFile(@RequestParam ("filename") String filename  )throws FileDownloadException, IOException{
        Object response = imageService.downloadFile(filename);
        if(response!=null){
            return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +filename + "\"").body(response);
        }else{
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .message("File could not be downloaded")
                    .isSuccess(false)
                    .status(400)
                    .build();
            return new ResponseEntity<>(genericResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/metadatas/{propId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse<Object>> getImageMetadata(@PathVariable String propId){

        List<Image> imageList = imageService.findImagesMetadata(propId);
        if(imageList.isEmpty()){
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .message("Property not found")
                    .data(new ArrayList<>())
                    .isSuccess(false)
                    .status(404)
                    .build();
            return new ResponseEntity<>(genericResponse,HttpStatus.NOT_FOUND);
        }else{
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .message("Metadatas")
                    .data(imageList)
                    .isSuccess(true)
                    .status(200)
                    .build();
            return new ResponseEntity<>(genericResponse,HttpStatus.OK);
        }
    }

}
