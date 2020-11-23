package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Client;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return em.createQuery("from Client", Client.class).getResultList();
	}

}
