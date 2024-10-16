package com.example.paris_janitor_api.config;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.example.paris_janitor_api.repository")
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), database);
    }
}
