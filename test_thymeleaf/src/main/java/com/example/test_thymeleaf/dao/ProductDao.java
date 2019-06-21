package com.example.test_thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.test_thymeleaf.domain.Product;

public interface ProductDao extends CrudRepository<Product,Long>{
	
	@Query("select p from Product p where p.description like %?1%")
	public List<Product>findByName(String name);
	
	public List<Product>findByDescriptionLikeIgnoreCase(String name);

}
