package com.example.paris_janitor_api.controller;

import com.example.paris_janitor_api.service.impl.S3Service;
import com.example.paris_janitor_api.util.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("/api/file")
public class FileController {

    private final S3Service s3Service;

    public FileController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<GenericResponse<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try{
            String key = this.s3Service.uploadFile(file);
            GenericResponse<String> genericResponse = new GenericResponse<>(true,"Upload success");
            return new ResponseEntity<>(genericResponse,HttpStatus.OK);
        }catch(Exception e){
            GenericResponse<String> genericResponse = new GenericResponse<>(false,e.getMessage());
            return new ResponseEntity<>(genericResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<GenericResponse<String>> getFileUrl(@PathVariable String fileName) {
        if(fileName.isEmpty()){
            GenericResponse<String> genericResponse = new GenericResponse<>(false,"File name is empty");
            return new ResponseEntity<>(genericResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            String url = this.s3Service.getFileUrl(fileName);
            if (url.isEmpty()) {
                GenericResponse<String> genericResponse = new GenericResponse<>(false,"File url is empty");
                return new ResponseEntity<>(genericResponse,HttpStatus.NOT_FOUND);
            }
            GenericResponse<String> genericResponse = new GenericResponse<>(true,"Download success",url);
            return new ResponseEntity<>(genericResponse,HttpStatus.OK);
        }catch (Exception e){
            GenericResponse<String> genericResponse = new GenericResponse<>(false,e.getMessage());
            return new ResponseEntity<>(genericResponse,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
