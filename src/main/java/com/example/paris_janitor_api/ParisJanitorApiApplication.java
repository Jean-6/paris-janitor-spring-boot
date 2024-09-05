package com.example.paris_janitor_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.paris_janitor_api.repository")
public class ParisJanitorApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParisJanitorApiApplication.class, args);
	}

}
