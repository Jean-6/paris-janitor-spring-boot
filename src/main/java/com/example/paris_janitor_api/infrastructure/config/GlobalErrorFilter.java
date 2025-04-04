package com.example.paris_janitor_api.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class GlobalErrorFilter implements WebFilter {

    private final ObjectMapper objectMapper;

    public GlobalErrorFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .onErrorResume(ex -> {
                    // Object creating
                    ApiError apiError = new ApiError(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Internal Server Error",
                            ex.getMessage(),
                            exchange.getRequest().getPath().toString());
                    // Serialize ApiError into JSON
                    byte[] errorResponse = null;
                    try {
                        errorResponse = objectMapper.writeValueAsBytes(apiError);
                    } catch (Exception e) {
                            e.printStackTrace();
                    }

                    exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                    return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                            .bufferFactory().wrap(errorResponse)));
                });
    }
}
