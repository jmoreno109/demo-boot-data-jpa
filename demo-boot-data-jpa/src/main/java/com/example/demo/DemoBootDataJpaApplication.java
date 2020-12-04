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


// it can be deployed as a WAR
//extends SpringBootServletInitializer


//Use
//org.springframework.boot.web.support.SpringBootServletInitializer

//For SpringBoot 2.0
//org.springframework.boot.web.servlet.support.SpringBootServletInitializer

/*
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
*/