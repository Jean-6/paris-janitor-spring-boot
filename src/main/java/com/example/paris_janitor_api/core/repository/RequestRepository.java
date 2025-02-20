package com.example.paris_janitor_api.core.repository;

import com.example.paris_janitor_api.core.model.Request;

import java.util.List;

public interface RequestRepository {
    Request save(Request booking);
    List<Request> findAll() ;
    Request findById(String id);
}
