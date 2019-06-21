package com.example.test_thymeleaf.repository;
import com.example.test_thymeleaf.domain.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill,Long>{

}
