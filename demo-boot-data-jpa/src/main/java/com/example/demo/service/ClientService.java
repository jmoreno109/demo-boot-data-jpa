package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.Client;

public interface ClientService {

	List<Client> findAll();

	void save(Client client);

	void deleteById(Integer id);

	Optional<Client> findById(Integer id);

}
