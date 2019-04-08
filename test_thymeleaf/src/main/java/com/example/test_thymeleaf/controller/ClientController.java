package com.example.test_thymeleaf.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import com.example.test_thymeleaf.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController 
{
	private static final String UPLOAD_DIR="uploads";
	@Autowired
	private ClientService cd;
	private final Logger log= LoggerFactory.getLogger(getClass());
	
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
				Path photoPath = Paths.get(UPLOAD_DIR).resolve(client.getImage()).toAbsolutePath();
				File file = photoPath.toFile();
				if(file.exists() || file.canRead())
				{
					if(file.delete())
					{
						flash.addFlashAttribute("info","Old photo "+client.getImage()+" has been deleted successfully");
					}
				}
			}
			String uniqueFileName=UUID.randomUUID().toString()+image.getOriginalFilename().replaceAll("\\s", "");
			Path rootPath = Paths.get(UPLOAD_DIR).resolve(uniqueFileName);
			Path absolutePath = rootPath.toAbsolutePath();
			log.info("rootpath: "+rootPath);
			log.info("absolutepath: "+absolutePath);
			try {
				Files.copy(image.getInputStream(),absolutePath);
				flash.addFlashAttribute("info","Photo uploaded successfully:"+uniqueFileName);
				client.setImage(uniqueFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			Path photoPath = Paths.get(UPLOAD_DIR).resolve(client.getImage()).toAbsolutePath();
			File file = photoPath.toFile();
			if(file.exists() || file.canRead())
			{
				if(file.delete())
				{
					flash.addFlashAttribute("info","Photo "+client.getImage()+" has been deleted successfully");
				}
			}
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
		Path photoPath = Paths.get(UPLOAD_DIR).resolve(filename).toAbsolutePath();
		Resource resource =null;
		try {
			resource = new UrlResource(photoPath.toUri());
			if(!resource.exists() || !resource.isReadable())
			{
				throw new RuntimeException("Runtime Exception: Unable to upload resource: "+filename);
			}
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
