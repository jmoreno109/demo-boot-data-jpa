package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.model.entity.Client;
import com.example.demo.model.entity.Product;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Client client) {
		clientRepository.save(client);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		clientRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Client> findById(Integer id) {
		return clientRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findByName(String term) {
		return productRepository.findByName(term);
	}

}
