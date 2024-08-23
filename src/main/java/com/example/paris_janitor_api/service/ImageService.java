package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.exception.FileDownloadException;
import com.example.paris_janitor_api.exception.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ImageService {
    String uploadFile(MultipartFile multipartFile,String propertyID) throws FileUploadException, IOException;
    Object downloadFile(String fileName) throws FileDownloadException, IOException;
    String getFileExtension(MultipartFile multipartFile);
    boolean isValidFile(MultipartFile multipartFile);
}
