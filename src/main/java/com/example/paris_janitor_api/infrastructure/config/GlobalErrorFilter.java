package com.example.paris_janitor_api.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.NonNullApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@NonNullApi
@Component
@Slf4j
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
                    ResponseWrapper<Void> responseWrapper = new ResponseWrapper<>(
                            false,
                            "Internal Server Error",
                            null,
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            exchange.getRequest().getPath().toString());
                    // Serialize ApiError into JSON
                    byte[] errorResponse ;
                    try {
                        errorResponse = objectMapper.writeValueAsBytes(responseWrapper);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        errorResponse = "{\"message\":\"Erreur interne du serveur\"}".getBytes(StandardCharsets.UTF_8) ;
                    }
                    // Définir le Content-Type JSON
                    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                    return exchange.getResponse().writeWith(
                            Mono.just(exchange.getResponse().bufferFactory().wrap(errorResponse)));
                });
    }
}
