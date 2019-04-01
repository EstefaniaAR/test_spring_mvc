package com.example.test_thymeleaf.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.test_thymeleaf.domain.Client;

public interface ClientService 
{
	public List<Client>findAll();
	public Page<Client>findAll(Pageable pageable);
	public void save(Client client);
	public Client findById(Long id);
	public void delete(Long id);

}
