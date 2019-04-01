package com.example.test_thymeleaf.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.test_thymeleaf.domain.Client;

public interface ClientDao extends PagingAndSortingRepository<Client,Long>
{

}
