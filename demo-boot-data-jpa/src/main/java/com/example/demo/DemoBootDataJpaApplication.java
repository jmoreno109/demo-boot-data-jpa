package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoBootDataJpaApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoBootDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("!!!ejecutando!!!");
	}

}
