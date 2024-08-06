package com.example.paris_janitor_api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.paris_janitor_api.service.S3Service;
import com.example.paris_janitor_api.util.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class S3ServiceImpl implements S3Service {

    private final String bucketName;
    private final AmazonS3 amazonS3;

    /*@Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;*/

    @Autowired
    public S3ServiceImpl(@Value("${aws.s3.bucket.name}") String bucketName, AmazonS3 amazonS3) {
        this.bucketName = bucketName;
        this.amazonS3 = amazonS3;
    }


    @Override
    public String uploadFile(MultipartFile multipartFile,String filePath) throws FileUploadException {//throws FileUploadException //(File file , String filename)

        //FileUploadResponse fileUploadResponse = new FileUploadResponse();
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //String todayDate = dateTimeFormatter.format(LocalDate.now());
        //String filePath="";
        try{
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            //filePath = todayDate+"/"+multipartFile.getOriginalFilename();
            amazonS3.putObject(bucketName,filePath,multipartFile.getInputStream(),objectMetadata);
            //fileUploadResponse.setFilePath(filePath);
            //fileUploadResponse.setDateTime(LocalDateTime.now());
        }catch(IOException e){
            log.error("Error occured ==> {} ",e.getMessage());
            throw new FileUploadException("Error occured in file upload ====> "+e.getMessage());
        }

        return amazonS3.getUrl(bucketName,filePath).toString();
    }

    @Override
    public File convertMultipartToFile(MultipartFile multipartFile) throws IOException {

        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return convFile;
    }

}
