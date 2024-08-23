package com.example.paris_janitor_api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.paris_janitor_api.exception.FileDownloadException;
import com.example.paris_janitor_api.exception.FileUploadException;
import com.example.paris_janitor_api.model.Image;
import com.example.paris_janitor_api.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @org.springframework.beans.factory.annotation.Value("${aws.bucket.name}")

    private String bucketName;
    @Autowired
    private final AmazonS3 s3Client;


    @Override
    public String uploadFile(MultipartFile multipartFile, String propertyId) throws FileUploadException, IOException {

        File file = new File(multipartFile.getOriginalFilename());
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(multipartFile.getBytes());
        }
        String filename = generateFileName(multipartFile);
        PutObjectRequest request = new PutObjectRequest(bucketName,filename,file);
        ObjectMetadata metadata =  new ObjectMetadata();
        metadata.setContentType("plain/");
        metadata.addUserMetadata("Title","File Upload - "+filename);
        metadata.setContentLength(file.length());
        request.setMetadata(metadata);
        s3Client.putObject(request);
        //Create Image metadata object to store in MongoDB
        Image img = new Image();
        img.setFilename(filename);
        img.setFileSize(multipartFile.getSize());
        img.setContentType(multipartFile.getContentType());
        img.setContent(multipartFile.getBytes());
        img.setPropertyID(propertyId);
        //image.set
        return  filename;
    }

    @Override
    public Object downloadFile(String fileName) throws FileDownloadException, IOException {

        S3Object object = s3Client.getObject(bucketName,fileName);
        try(S3ObjectInputStream s3ObjectInputStream = object.getObjectContent()){
            try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
                byte[] read_buf = new byte[1024];
                int read_len = 0;
                while((read_len = s3ObjectInputStream.read(read_buf)) > 0){
                    fileOutputStream.write(read_buf,0,read_len);
                }
            }
            Path pathObject = Paths.get(fileName);
            Resource resource = new UrlResource(pathObject.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new FileDownloadException("Could not find file!");
            }
        }
    }

    @Override
    public String getFileExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (!originalFilename.isEmpty()  && originalFilename.contains(".")) { //Or !=null
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        } else {
            // Handle the case where there's no extension or filename is null
            String contentType = multipartFile.getContentType();
            if(!contentType.isEmpty()){
                switch(contentType){
                    case MediaType.IMAGE_JPEG_VALUE:
                        return "jpeg";
                    case MediaType.IMAGE_PNG_VALUE:
                        return "png";
                    default:
                        return "";// Handle unknown content types
                }
            }else{
                return "";// Handle the case where both filename and content type are unavailable
            }
        }
    }

    @Override
    public boolean isValidFile(MultipartFile multipartFile) {
        log.info("Empty Status ==> {}", multipartFile.isEmpty());
        if (Objects.isNull(multipartFile.getOriginalFilename())){
            return false;
        }
        return !multipartFile.getOriginalFilename().trim().equals("");
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
}
