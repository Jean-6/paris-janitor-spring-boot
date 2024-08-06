package com.example.paris_janitor_api.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
//@ActiveProfiles({ "dev" })
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

    /*@Bean
    public MongoClientFactoryBean mongo(){
        MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
        ConnectionString connectionString = new ConnectionString(uri);
        mongo().setConnectionString(connectionString);
        return mongoClientFactoryBean;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception{
        return new MongoTemplate((MongoDatabaseFactory) MongoClients.create(uri));
    }*/
    /*@Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("localhost");
        return mongo;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(MongoClients.create(uri), database);
    }*/
    /*public @Bean com.mongodb.client.MongoClient mongoClient() {
        return com.mongodb.client.MongoClients.create("mongodb://localhost:27017");
    }*/
}
