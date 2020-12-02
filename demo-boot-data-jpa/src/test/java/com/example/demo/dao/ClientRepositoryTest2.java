package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.entity.Client;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) 
public class ClientRepositoryTest2 {
	
	Logger logger = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	void should_findById() {
		Client client = clientRepository.findById(1).get();
		assertThat(client).isNotNull();
		
		logger.info("*****************");
		logger.info(client.toString());
		client.getInvoiceList().forEach(x -> logger.info(x.toString()));
		logger.info("*****************");
	}

}
