package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.Client;

public interface ClientDAO {

	List<Client> findAll();

	void save(Client client);
	
	void delete(Integer id);
	
	Optional<Client> getById(Integer id);

}
