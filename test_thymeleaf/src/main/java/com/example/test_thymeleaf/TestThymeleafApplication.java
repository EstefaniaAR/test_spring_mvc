package com.example.test_thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.test_thymeleaf.service.IUploadFileService;

@SpringBootApplication
public class TestThymeleafApplication implements CommandLineRunner{

	@Autowired
	IUploadFileService fs;
	public static void main(String[] args) {
		SpringApplication.run(TestThymeleafApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fs.deleteAll();
		fs.init();
		
	}

}
