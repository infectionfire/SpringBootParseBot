package com.example;


import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.apache.logging.log4j.LogManager.getLogger;

@SpringBootApplication
public class ExampleBotApplication {

	private static final Logger log = getLogger(ExampleBotApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ExampleBotApplication.class, args);
	}

}
