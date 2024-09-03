package com.example.paris_janitor_api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.example.paris_janitor_api.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class BucketServiceImpl implements BucketService {

    //Logger LOG = LogManager.getLogger(BucketServiceImpl.class);

    @Autowired
    AmazonS3 s3Client;

    @Override
    public List<Bucket> getBucketList() {
        log.info("getting bucket list... ");
        return s3Client.listBuckets();
    }

    @Override
    public boolean validateBucket(String bucketName) {
        return false;
    }

    @Override
    public void getObjectFromBucket(String bucketName, String objectName) throws IOException {

    }

    @Override
    public void putObjectIntoBucket(String bucketName, String objectName, String filePathToUpload) {

    }

    @Override
    public void createBucket(String bucket) {

    }
}
