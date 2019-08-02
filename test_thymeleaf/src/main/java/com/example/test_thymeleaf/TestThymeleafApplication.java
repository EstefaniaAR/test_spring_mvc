package com.example.test_thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.test_thymeleaf.service.IUploadFileService;

@SpringBootApplication
public class TestThymeleafApplication implements CommandLineRunner
{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Autowired
	IUploadFileService fs;
	public static void main(String[] args) {
		SpringApplication.run(TestThymeleafApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception 
	{
		fs.deleteAll();
		fs.init();
		
		String password ="12345";
		for (int x=0; x < 2; x++)
		{
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}

}
