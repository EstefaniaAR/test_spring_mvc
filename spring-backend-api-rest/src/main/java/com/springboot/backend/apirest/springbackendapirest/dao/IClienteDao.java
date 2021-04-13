package com.springboot.backend.apirest.springbackendapirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.backend.apirest.springbackendapirest.domain.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long>{
	
}
