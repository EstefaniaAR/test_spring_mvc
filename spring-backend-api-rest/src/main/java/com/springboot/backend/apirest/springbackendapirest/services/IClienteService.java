package com.springboot.backend.apirest.springbackendapirest.services;

import java.util.List;

import com.springboot.backend.apirest.springbackendapirest.domain.Cliente;

public interface IClienteService{
	public List<Cliente>findAll();
	public Cliente save (Cliente cliente);
	public void delete (Long id);
	public Cliente findById(Long id);
	

}
