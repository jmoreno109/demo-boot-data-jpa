package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
//@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = Replace.NONE) // for MYSQL
//@Disabled("Disabled until bug #2019 has been fixed!")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientRepositoryTest {

	Logger logger = LoggerFactory.getLogger(ClientRepositoryTest.class);

	@Autowired
	private ClientRepository clientRepository;

	@BeforeEach
	public void runBeforeTestMethod() {
		logger.info("@BeforeEach - runBeforeTestMethod");
	}

	@Test
	// @Sql("import.sql") // inside the same package
	@Order(1)
	void should_find_All() {
		List<Client> listClient = (List<Client>) clientRepository.findAll();
		assertThat(listClient).isNotNull();
	}

	@Test
	@Order(2)
	void should_get_by_LastName() {

		List<Client> listClient = (List<Client>) clientRepository.getByLastName("santos");
		assertThat(listClient).hasSize(3);

		logger.info("*************************************");
		listClient.forEach(x -> logger.info(x.toString()));
		logger.info("*************************************");
	}

	@Test
	//@Disabled("Disabled until CustomerService is up!")
	@Order(3)
	void should_save() {

		Client client = new Client();
		client.setName("tomas");
		client.setLastName("santos");
		client.setEmail("tomas@correo.com");
		client.setDtCreation(new Date());
		clientRepository.save(client);

		assertThat(client.getId()).isNotNull();

		logger.info("*****************");
		logger.info(client.toString());
		logger.info("*****************");

	}

	@AfterEach
	public void runAfterTestMethod() {
		logger.info("@AfterEach - runBeforeTestMethod");
	}

}
