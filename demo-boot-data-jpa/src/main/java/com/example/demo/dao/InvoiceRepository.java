package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
	
	@Query("select i from Invoice i join fetch i.client c join fetch i.invoiceItemList l join fetch l.product where i.id = ?1 ")
	public Invoice fetchById(Long id);

}
