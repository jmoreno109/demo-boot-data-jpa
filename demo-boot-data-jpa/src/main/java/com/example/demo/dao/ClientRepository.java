package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

	public List<Client> findByName(String name);

	@Query("select c from Client c where c.lastName = ?1")
	public List<Client> getByLastName(String lastName);
	
	//@Query("select c from Client c where c.lastName = :lastName")
	//public List<Client> findByLastName(@Param("lastName") String lastName);

}
