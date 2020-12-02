package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.entity.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) 
public class ProductRepositoryTest {
	
	Logger logger = LoggerFactory.getLogger(ClientRepositoryTest.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void should_findByName() {
		List<Product> productList = productRepository.findByName("pa");
		assertThat(productList).isNotEmpty();
		
	}

}
