package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.util.FileUploadResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface S3Service {

    String uploadFile(MultipartFile multipartFile,String filePath) throws FileUploadException;
    //FileUploadResponse uploadFile(MultipartFile multipartFile) throws FileUploadException;//throws FileUploadException;
    File convertMultipartToFile(MultipartFile multipartFile) throws IOException;//throws IOException;
}
