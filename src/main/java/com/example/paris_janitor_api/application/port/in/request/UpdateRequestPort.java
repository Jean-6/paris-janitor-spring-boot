package com.example.paris_janitor_api.application.port.in.request;

import com.example.paris_janitor_api.core.model.Request;

public interface UpdateRequestPort {
    Request updateRequest(String id, Request request );
}
