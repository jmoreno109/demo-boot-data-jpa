package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

import com.example.demo.model.entity.Client;
import com.example.demo.service.ClientService;

public class ClientServiceTest {

	private static Logger logger = LoggerFactory.getLogger(ClientServiceTest.class);
	private GenericApplicationContext ctx;

	private ClientService clientService;

	@BeforeEach
	public void setUp() {
		// ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		clientService = ctx.getBean(ClientService.class);
		assertNotNull(clientService);
	}

	@Test
	public void test() {

		List<Client> clientList = clientService.findAll();
		clientList.size();
		// assertEquals(3, clientList.size());
		listSingers(clientList);
	}

	private static void listSingers(List<Client> singers) {
		logger.info(" ---- Listing singers:");
		for (Client singer : singers) {
			logger.info(singer.toString());
		}
	}

	@AfterEach
	public void tearDown() {
		ctx.close();
	}

}
