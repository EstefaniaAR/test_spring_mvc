package com.example.test_thymeleaf.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.test_thymeleaf.dao.ClientDao;
import com.example.test_thymeleaf.domain.Client;

@Repository
public class ClientRepository implements ClientDao
{
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Client> findAll() 
	{
		return em.createQuery("from Client").getResultList();
	}

	@Override
	@Transactional
	public void save(Client client) 
	{
		if(client.getId() >0)
			em.merge(client);
		else
			em.persist(client);
	}

	@Override
	@Transactional
	public Client findById(Long id)
	{
		return em.find(Client.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		Client client = findById(id);
		em.remove(client);
	}
}
