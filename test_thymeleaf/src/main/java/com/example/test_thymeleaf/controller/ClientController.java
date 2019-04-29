package com.example.test_thymeleaf.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.test_thymeleaf.domain.Client;
import com.example.test_thymeleaf.service.ClientService;
import com.example.test_thymeleaf.service.IUploadFileService;
import com.example.test_thymeleaf.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController 
{
	@Autowired
	private ClientService cd;
	
	@Autowired
	IUploadFileService fs;
	
	@GetMapping("/clients")
	public String list(@RequestParam (name="page", defaultValue="0")int page,Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Client> clients = cd.findAll(pageRequest);
		PageRender <Client> pageRender = new PageRender<>("/clients",clients);
		model.addAttribute("client_title","List of Clients");
		model.addAttribute("clients",clients);
		model.addAttribute("page",pageRender);
		return "clients";
	}
	
	@GetMapping("/form")
	public String insert (Map<String,Object> model)
	{
		Client client = new Client();
		model.put("title", "Client Form");
		model.put("client", client);
		model.put("button", "Create");
		return "form";
	}
	@PostMapping("/form")
	public String insert(@Valid Client client, BindingResult result,@RequestParam("file") MultipartFile image,Map <String,Object>model,RedirectAttributes flash, SessionStatus status )
	{
		if(result.hasErrors())
		{
			model.put("title", "Client Form");
			model.put("button", "Create");
			return "form";
		}
		
		if(!image.isEmpty())
		{
			if(client.getId() >0
					 && client.getImage()!=null
					 && client.getImage().length()>0)
			{
				fs.delete(client.getImage());

			}
			String uniqueFileName=null;
			try {
				uniqueFileName = fs.copy(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
			flash.addFlashAttribute("info","Photo uploaded successfully:"+uniqueFileName);
			client.setImage(uniqueFileName);
			
		}
		cd.save(client);
		flash.addFlashAttribute("success","Cliente has been created successfully");
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
		model.put("button", "Edit");
		return "form";
	}
	
	@GetMapping("/erase/{id}")
	public String erase (@PathVariable(value="id") Long id, Map<String,Object>model,RedirectAttributes flash)
	{
		if(id >0)
		{
			Client client = cd.findById(id);
			cd.delete(id);
			fs.delete(client.getImage());
			if(fs.delete(client.getImage()))
				flash.addFlashAttribute("info","Photo "+client.getImage()+" has been deleted successfully");
		}
		flash.addFlashAttribute("success","Client has been deleted successfuly");
		return "redirect:/clients";
	}

	@GetMapping("clientDetails/{id}")
	public String getDetais(@PathVariable (name="id")Long id,Map <String, Object>model,RedirectAttributes flash)
	{
		Client client = cd.findById(id);
		if(client ==null)
		{
			flash.addFlashAttribute("error","Client not found");
			return "redirect:/clients";
		}
		model.put("client", client);
		model.put("title", "Client Details "+client.getName());
		return "clientDetails" ;
	}
	
	@GetMapping("/uploads/{filename:.+}")
	public ResponseEntity<Resource> seePhoto(@PathVariable String filename)
	{
		Resource resource = null;
		try {
			resource = fs.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename:\""+resource.getFilename()+"\"")
				.body(resource);
	}
}
