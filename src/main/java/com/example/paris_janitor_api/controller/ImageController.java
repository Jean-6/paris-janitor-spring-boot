package com.example.paris_janitor_api.controller;


import com.amazonaws.services.s3.AmazonS3;
import com.example.paris_janitor_api.exception.FileDownloadException;
import com.example.paris_janitor_api.exception.FileEmptyException;
import com.example.paris_janitor_api.exception.FileUploadException;
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


    @PostMapping(value="/upload/{propertyId}",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartFile, @PathVariable String propertyId) throws FileEmptyException, FileUploadException, IOException {

        boolean isPropertyExists = propertyService.existsPropertyById(propertyId);
        if(!isPropertyExists){
            GenericResponse genericResponse = GenericResponse.builder()
                    .message("ID property :"+propertyId+" does not exist")
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
            String fileName = imageService.uploadFile(multipartFile,propertyId);
            GenericResponse genericResponse = GenericResponse.builder()
                    .message("file uploaded successfully. File unique name =>" + fileName)
                    .isSuccess(true)
                    .status(200)
                    .build();
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        }else{
            GenericResponse genericResponse = GenericResponse.builder()
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
            GenericResponse genericResponse = GenericResponse.builder()
                    .message("File could not be downloaded")
                    .isSuccess(false)
                    .status(400)
                    .build();
            return new ResponseEntity<>(genericResponse,HttpStatus.NOT_FOUND);
        }
    }

}
