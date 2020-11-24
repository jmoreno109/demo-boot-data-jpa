package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.entity.Client;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClientRepositoryTest {

	Logger logger = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	private ClientRepository clientRepository;

	@Test
	// @Sql("import.sql") // inside the same package
	void should_find_All() {
		List<Client> listClient = (List<Client>) clientRepository.findAll();
		assertThat(listClient).isNotNull();
	}

	@Test
	void should_get_by_LastName() {

		List<Client> listClient = (List<Client>) clientRepository.getByLastName("santos");
		assertThat(listClient).hasSize(2);

		listClient.forEach(x -> logger.info(x.toString()));
	}

	@Test
	void should_save() {

		Client client = new Client();
		client.setName("tomas");
		client.setLastName("santos");
		client.setEmail("tomas@correo.com");
		client.setDtCreation(new Date());
		clientRepository.save(client);

		assertThat(client.getId()).isNotNull();

		logger.info(client.toString());

	}

}
