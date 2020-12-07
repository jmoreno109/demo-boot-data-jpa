package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ClientRepository;
import com.example.demo.model.entity.Client;

@RestController
@RequestMapping("api/clients")
public class ClientRestController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@GetMapping("/list")
	public List<Client> list(){
		return (List<Client>) clientRepository.findAll();
	}

}
