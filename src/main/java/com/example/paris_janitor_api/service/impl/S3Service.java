package com.example.paris_janitor_api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Paths;


@Service
public class S3Service  {

    private final S3Client s3Client;

    @org.springframework.beans.factory.annotation.Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {

        String key = Paths.get("pdfs",file.getOriginalFilename()).toString();
        /*PutObjectRequest putObjectRequest =PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();
        this.s3Client.putObject(putObjectRequest,RequestBody.fromBytes(file.getBytes()));*/

        this.s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes())
        );

        return key;
    }

    public String getFileUrl(String key) {
        //return String.format("s3://%s/%s", bucketName, key);
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, key);
    }
}
