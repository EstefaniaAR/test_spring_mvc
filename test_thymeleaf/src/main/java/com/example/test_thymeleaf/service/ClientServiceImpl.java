package com.example.test_thymeleaf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_thymeleaf.dao.ClientDao;
import com.example.test_thymeleaf.domain.Client;

@Service
public class ClientServiceImpl implements ClientService
{

	@Autowired
	ClientDao cd;
	
	@Override
	@Transactional
	public List<Client> findAll() 
	{
		return cd.findAll();
	}

	@Override
	@Transactional
	public void save(Client client) 
	{
		cd.save(client);
	}

	@Override
	@Transactional
	public Client findById(Long id) 
	{
		return cd.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		cd.delete(id);
	}
	
}
