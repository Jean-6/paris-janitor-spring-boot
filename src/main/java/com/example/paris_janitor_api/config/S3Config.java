package com.example.paris_janitor_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


@Configuration
public class S3Config {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    //@Value("${aws.region.name}")
    //private String regionName;

    @Bean
    public S3Client s3Client() {
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey)
        );
        return S3Client.builder()
                .region(Region.of("eu-north-1"))
                .credentialsProvider(credentialsProvider)
                .build();
    }
}