package com.example.paris_janitor_api.infrastructure.doc;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info().description("API Documentation")
                        .version("1.0.0")
                        .description("Documentation d'API REST - Gestion de biens")
                        .contact(new Contact()
                                .name("Jaures Support Dev")
                                .email("oka.jeanjaures@gmail.com")
                                .email("https://github.com/Jean-6/paris-janitor-spring-boot")
                        )
                );
    }
}
