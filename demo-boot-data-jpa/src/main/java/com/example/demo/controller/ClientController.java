package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.entity.Client;
import com.example.demo.service.ClientService;

@Controller
public class ClientController {

	Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("clients", clientService.findAll());
		return "list";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "form";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Integer id) {
		Client client = clientService.findById(id).orElseThrow();
		model.addAttribute("client", client);
		return "form";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		clientService.deleteById(id);
		return "redirect:/list";
	}

	@PostMapping("/save")
	public String save(@Valid Client client, BindingResult result) {
		if (result.hasErrors()) {
			return "form";
		}
		clientService.save(client);
		return "redirect:/list";
	}

}
