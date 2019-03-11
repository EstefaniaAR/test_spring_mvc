package com.example.test_thymeleaf.dao;


import org.springframework.data.repository.CrudRepository;

import com.example.test_thymeleaf.domain.Client;

public interface ClientDao extends CrudRepository<Client,Long>
{

}
