package com.example.paris_janitor_api.core.service;

import com.example.paris_janitor_api.core.model.Request;

import java.util.List;

public interface RequestService {


    Request createProperty(Request request);
    List<Request> getAllRequests();
    Request getRequestById(String id);

}
