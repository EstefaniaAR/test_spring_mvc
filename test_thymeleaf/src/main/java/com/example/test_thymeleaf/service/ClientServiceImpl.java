package com.example.test_thymeleaf.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test_thymeleaf.dao.ClientDao;
import com.example.test_thymeleaf.dao.ProductDao;
import com.example.test_thymeleaf.domain.Bill;
import com.example.test_thymeleaf.domain.Client;
import com.example.test_thymeleaf.domain.Product;
import com.example.test_thymeleaf.repository.BillRepository;
import com.example.test_thymeleaf.repository.ClientRepository;
import com.example.test_thymeleaf.repository.ProductRepository;

@Service
public class ClientServiceImpl implements ClientService
{

	@Autowired
	private ClientRepository cd;
	
	@Autowired
	private ProductRepository pd;
	
	@Autowired
	private BillRepository br;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() 
	{
		return (List<Client>)cd.findAll();
	}

	@Override
	@Transactional
	public void save(Client client) 
	{
		cd.save(client);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) 
	{
		return cd.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		cd.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable pageable) {
		return cd.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findByName(String term) {
		return pd.findByName(term);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> findByDescriptionLikeIgnoreCase(String name) {
		return pd.findByDescriptionLikeIgnoreCase("%"+name+"%");
	}

	@Override
	@Transactional
	public void saveBill(Bill bill) {
		// TODO Auto-generated method stub
		br.save(bill);
	}

	@Override
	@Transactional(readOnly=true)
	public Product findProductById(Long id)
	{
		return pd.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Bill findBillById(Long id) {
		// TODO Auto-generated method stub
		return br.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteBill(Long id) {
		br.deleteById(id);
		
	}

	
}
