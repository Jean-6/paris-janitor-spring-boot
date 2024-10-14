package com.example.paris_janitor_api.service;

import com.example.paris_janitor_api.model.Supporting;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SupportingService {


    Supporting insertSupporting(String userId, MultipartFile file);
    Supporting findSupporting(String userId, String name);
    List<Supporting> findSupportings(String userId);


}
