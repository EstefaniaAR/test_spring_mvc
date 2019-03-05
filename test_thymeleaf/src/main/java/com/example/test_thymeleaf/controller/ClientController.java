package com.example.test_thymeleaf.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.test_thymeleaf.dao.ClientDao;
import com.example.test_thymeleaf.domain.Client;

@Controller
@SessionAttributes("client")
public class ClientController 
{
	@Autowired
	private ClientDao cd;
	
	@GetMapping("/clients")
	public String list(Model model)
	{
		model.addAttribute("client_title","List of Clients");
		model.addAttribute("clients",cd.findAll());
		return "clients";
	}
	
	@GetMapping("/form")
	public String insert (Map<String,Object> model)
	{
		Client client = new Client();
		model.put("title", "Client Form");
		model.put("client", client);
		return "form";
	}
	@PostMapping("/form")
	public String insert(@Valid Client client, BindingResult result, Model model,SessionStatus status )
	{
		if(result.hasErrors())
		{
			model.addAttribute("title", "Client Form");
			return "form";
		}
		cd.save(client);
		status.setComplete();
		return "redirect:clients";
	}
	
	@GetMapping("/form/{id}")
	public String modify(@PathVariable(value="id") long id, Map<String,Object>model)
	{
		Client client =null;
		if(id> 0)
		{
			client = cd.findById(id);
		}
		else
			return "redirect;/clients";
		model.put("client", client);
		model.put("title", "Edit Client");
		return "form";
	}
	
	@GetMapping("/erase/{id}")
	public String erase (@PathVariable(value="id") Long id, Map<String,Object>model)
	{
		if(id >0)
			cd.delete(id);
		return "redirect:/clients";
	}

}
