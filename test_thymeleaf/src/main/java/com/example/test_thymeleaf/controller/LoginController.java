package com.example.test_thymeleaf.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required =false) String error,@RequestParam(value="logout", required =false) String logout, Model model, Principal principal,RedirectAttributes flash)
	{
		if(principal !=null)
		{
			flash.addFlashAttribute("info","You has been signed in as "+principal.getName());
			return "redirect:/";
		}
		
		if(error!=null)
		{
			log.info("Username or password incorrect");
			model.addAttribute("error","Username or password incorrect");
		}
		if(logout!=null)
		{
			model.addAttribute("success","You has been logged out sucessfuly");
		}
		return "login";
	}
	

}
