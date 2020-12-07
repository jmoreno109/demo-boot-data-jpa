package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Client;
import com.example.demo.model.entity.Invoice;
import com.example.demo.model.entity.InvoiceItem;
import com.example.demo.model.entity.Product;
import com.example.demo.service.ClientService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductService;

@Controller
//@Secured("ADMIN")
public class ClientController {

	Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	ProductService productService;

	@Secured({ "ROLE_USER" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("clients", clientService.findAll());
		return "list";
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/create")
	public String create(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "form";
	}

	@Secured({ "ROLE_USER" })
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Integer id) {
		// Client client = clientService.findById(id).orElseThrow();
		Client client = clientService.findById(id).get();
		model.addAttribute("client", client);
		return "form";
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		clientService.deleteById(id);
		return "redirect:/list";
	}

	@PostMapping("/save")
	public String save(@Valid Client client, BindingResult result, @RequestParam MultipartFile file,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			return "form";
		}

		if (!file.isEmpty()) {

			// Path path = Paths.get("src//main//resources//static/upload");
			// String rootPath = path.toFile().getAbsolutePath();
			String rootPath = "C://temp//upload"; // local directory

			Path pathAbs = Paths.get(rootPath + "//" + file.getOriginalFilename());
			try {
				Files.write(pathAbs, file.getBytes());
				flash.addFlashAttribute("info", "ha subido correctamente el archivo");
				client.setPhoto(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		clientService.save(client);
		return "redirect:/list";
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model, RedirectAttributes flash) {

		Client client = clientService.findById(id).get();
		if (client == null) {
			flash.addFlashAttribute("error", "Cliente no encontrado");
			return "redirect:/list";
		}

		model.addAttribute("client", client);

		return "show";
	}

	// produces = MediaType.TEXT_PLAIN_VALUE
	@GetMapping(value = "/load-products/{term}", produces = { "application/json" })
	public @ResponseBody List<Product> loadProduct(@PathVariable String term) {
		logger.info("***entro***");
		return clientService.findByName(term);
	}

	@GetMapping(value = "/save-invoice/{id}")
	@ResponseBody
	public void saveInvoice(@PathVariable Integer id, SessionStatus status) {

		Client client = clientService.findById(id).get();
		Product product = productService.findById(1L);

		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setQuantity(45);
		invoiceItem.setProduct(product);

		Invoice invoice = new Invoice();
		invoice.setClient(client);
		invoice.addInvoiceItemList(invoiceItem);
		invoice.setDescription("test_desc");
		invoice.setObservation("test_obs");

		invoiceService.save(invoice);
		status.setComplete();
	}

	@GetMapping(value = "/delete-client/{id}")
	@ResponseBody
	public void deleteClient(@PathVariable Integer id) {
		clientService.deleteById(id);
	}

}
