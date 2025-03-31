package com.example.paris_janitor_api.infrastructure.doc;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info().description("API Documentation")
                        .version("1.0.0")
                        .description("Documentation d'API REST - Gestion de biens")
                        .contact(new Contact()
                                .name("Jean-Jaures Support Dev")
                                .email("oka.jeanjaures@gmail.com")
                                .url("https://github.com/Jean-6/paris-janitor-spring-boot")
                        )
                );
    }

    @Bean
    public GroupedOpenApi apiGroup(){
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan("com.example.paris_janitor_api")
                .build();
    }

    @Bean
    OpenApiCustomizer openApiCustomizer(){
        return OpenApi -> OpenApi.getPaths().forEach((path,pathItem) ->{
            pathItem.readOperations().forEach(operation -> {
                operation.addTagsItem("Custom Tag");
            });
        });
    }
}
