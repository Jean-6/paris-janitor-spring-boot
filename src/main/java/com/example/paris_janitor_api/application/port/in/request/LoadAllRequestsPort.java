package com.example.paris_janitor_api.application.port.in.request;

import com.example.paris_janitor_api.core.model.Request;

import java.util.List;

public interface LoadAllRequestsPort {
    List<Request> getAllRequest();
}
