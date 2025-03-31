package com.example.paris_janitor_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.example.paris_janitor_api")
public class ParisJanitorApiApplication {

	static Logger logger = LoggerFactory.getLogger(ParisJanitorApiApplication.class);

	public static void main(String[] args) {

		logger.info("paris janitor api application started");

		SpringApplication.run(ParisJanitorApiApplication.class, args);
	}

}
