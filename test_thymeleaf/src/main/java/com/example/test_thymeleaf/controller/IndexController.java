package com.example.test_thymeleaf.controller;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController
{

	@Value(value = "${application.controller.title}")
	private String title;
	
	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("/home")
	public String getIndex(Model model)
	{
		model.addAttribute("title",this.title);
		return "layout/layout"; 
	}
	
	@GetMapping({"/","/greet"})
	public String getGreet(Model model,Authentication authentication,HttpServletRequest request)
	{
		if(authentication  != null )
		{
			logger.info(authentication.getName()+" has been logged "+new Date().toString());
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null)
		{
			logger.info(auth.getName()+" has been logged "+new Date().toString());
		}
		if(hasRoles("ROLE_ADMIN"))
			logger.info("admin access");
		else
			logger.info("normal access");
		
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,"ROLE_");
		if(securityContext.isUserInRole("ADMIN"))
			logger.info("admin access");
		else
			logger.info("normal access");
		
		if(request.isUserInRole("ROLE_ADMIN"))
			logger.info("admin access");
		else
			logger.info("normal access");
		model.addAttribute("title",this.title);
		return "greet"; 
	}
	

	private boolean hasRoles(String role)
	{
		SecurityContext context = SecurityContextHolder.getContext();
		if(context==null)
			return false;
		Authentication auth = context.getAuthentication();
		if(auth ==null)
			return false;
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		return authorities.contains(new SimpleGrantedAuthority(role));
		/*
		for(GrantedAuthority a: authorities)
		{
			if(role.equals(a.getAuthority()))
				return true;
		}
		return false;
		*/
	}
	
}
