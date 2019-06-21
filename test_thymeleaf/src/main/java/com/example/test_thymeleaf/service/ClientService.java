package com.example.test_thymeleaf.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.test_thymeleaf.domain.Bill;
import com.example.test_thymeleaf.domain.Client;
import com.example.test_thymeleaf.domain.Product;

public interface ClientService 
{
	public List<Client>findAll();
	public Page<Client>findAll(Pageable pageable);
	public void save(Client client);
	public Client findById(Long id);
	public void delete(Long id);
	public List<Product>findByName(String term);
	public List<Product>findByDescriptionLikeIgnoreCase(String name);
	public void saveBill(Bill bill);
	public Product findProductById(Long id);
	public Bill findBillById(Long id);
	public void deleteBill(Long id);
}
