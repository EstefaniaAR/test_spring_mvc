package com.example.test_thymeleaf.service;

import java.util.List;

import com.example.test_thymeleaf.domain.Client;

public interface ClientService 
{
	public List<Client>findAll();
	public void save(Client client);
	public Client findById(Long id);
	public void delete(Long id);

}
