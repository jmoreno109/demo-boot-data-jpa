package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.service.ClientService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" })
@Sql("/import.sql")
@AutoConfigureTestDatabase
public class ClientServiceTest2 {

	private static Logger logger = LoggerFactory.getLogger(ClientServiceTest.class);

	@Autowired
	ClientService clientService;

	@Test
	public void test() {
		logger.info("entro!!!!!!!!!!!!!");
		// assertEquals(3, countryService.findAll().size());
	}

}
