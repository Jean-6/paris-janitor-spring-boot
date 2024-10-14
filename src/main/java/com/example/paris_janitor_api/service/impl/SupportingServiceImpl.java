package com.example.paris_janitor_api.service.impl;

import com.example.paris_janitor_api.model.Supporting;
import com.example.paris_janitor_api.repository.SupportingRepository;
import com.example.paris_janitor_api.service.SupportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class SupportingServiceImpl implements SupportingService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SupportingRepository supportingRepository;


    @Override
    public Supporting insertSupporting(String userId, MultipartFile multipartFile) {
        Supporting supporting = new Supporting();
        supporting.setUserId(userId);
        supporting.setName(multipartFile.getOriginalFilename());
        supporting.setUrl("");
        return supportingRepository.save(supporting);
    }

    @Override
    public Supporting findSupporting(String userId, String name) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("userId").is(userId)
                .andOperator(Criteria.where("name").is(name));
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query,Supporting.class);
    }

    @Override
    public List<Supporting> findSupportings(String userId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("userId").is(userId));
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Supporting.class);
    }
}
