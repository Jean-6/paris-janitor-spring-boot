package com.example.paris_janitor_api.application.port.in.request;


import com.example.paris_janitor_api.core.model.Request;

import java.util.Optional;

public interface LoadRequestByIdPort {
    Optional<Request>  getRequestById(String id);
}
