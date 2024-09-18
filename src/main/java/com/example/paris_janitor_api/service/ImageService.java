package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.exception.FileDownloadException;
import com.example.paris_janitor_api.exception.FileUploadException;
import com.example.paris_janitor_api.model.Image;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    Image uploadFile(MultipartFile multipartFile, String propertyID) throws FileUploadException, IOException;
    Object downloadFile(String fileName) throws FileDownloadException, IOException;
    String getFileExtension(MultipartFile multipartFile);
    boolean isValidFile(MultipartFile multipartFile);
    List<Image> findImagesMetadata(String key);
}
