package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Client;

@Repository
public class ClientDAOImpl implements ClientDAO {

	final static String ALL_CLIENT_NATIVE_QUERY = "select id, name, last_name, email, dt_creation from clients";

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		 //return em.createNativeQuery(ALL_CLIENT_NATIVE_QUERY,
		 //Client.class).getResultList();
		return em.createQuery("from Client", Client.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Client> getById(Integer id) {
		Optional<Client> clientOpt = Optional.ofNullable(em.find(Client.class, id));
		return clientOpt;
	}

	@Override
	@Transactional
	public void save(Client client) {
		if (client.getId() == null) {
			em.persist(client);
		} else {
			em.merge(client);
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		em.remove(getById(id).get());
	}

}
