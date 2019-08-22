package com.example.test_thymeleaf.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.test_thymeleaf.domain.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
	public UserEntity findByUsername(String username);
}
