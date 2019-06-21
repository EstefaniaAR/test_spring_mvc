package com.example.test_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Value(value = "${application.controller.title}")
	private String title;
	
	@GetMapping("/home")
	public String getIndex(Model model)
	{
		model.addAttribute("title",this.title);
		return "layout/layout"; 
	}
	
}
