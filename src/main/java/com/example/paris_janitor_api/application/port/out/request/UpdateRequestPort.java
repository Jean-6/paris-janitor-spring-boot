package com.example.paris_janitor_api.application.port.out.request;

import com.example.paris_janitor_api.core.model.Request;

public interface UpdateRequestPort {
    Request findByIdAndUpdate(String id, Request request);
}