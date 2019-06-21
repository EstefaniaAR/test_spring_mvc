package com.example.test_thymeleaf.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.test_thymeleaf.domain.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client,Long>
{

}
