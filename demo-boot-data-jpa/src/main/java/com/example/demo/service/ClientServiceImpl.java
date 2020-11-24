package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.model.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<Client> findAll() {
		return (List<Client>) clientRepository.findAll();
	}

	@Override
	public void save(Client client) {
		clientRepository.save(client);
	}

	@Override
	public void deleteById(Integer id) {
		clientRepository.deleteById(id);
	}

	@Override
	public Optional<Client> findById(Integer id) {
		return clientRepository.findById(id);
	}

}
