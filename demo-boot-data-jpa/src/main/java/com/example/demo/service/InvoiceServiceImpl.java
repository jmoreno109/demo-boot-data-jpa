package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InvoiceRepository;
import com.example.demo.model.entity.Invoice;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public void save(Invoice invoice) {
		invoiceRepository.save(invoice);
	}
	
}
