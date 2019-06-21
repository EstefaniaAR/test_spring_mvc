package com.example.test_thymeleaf.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.test_thymeleaf.domain.Bill;
import com.example.test_thymeleaf.domain.BillItem;
import com.example.test_thymeleaf.domain.Client;
import com.example.test_thymeleaf.domain.Product;
import com.example.test_thymeleaf.service.ClientService;

@Controller
@RequestMapping(value="/bill")
@SessionAttributes("bill")
public class BillController {
	@Autowired
	public ClientService cs;
	private final Logger log= LoggerFactory.getLogger(getClass());
	
	@GetMapping("/form/{clientId}")
	public String getBill(@PathVariable (value="clientId") Long id,Map<String,Object> model,RedirectAttributes flash)
	{
		model.put("title", "Bill Form");
		Client client = cs.findById(id);
		if(client == null)
		{
			flash.addFlashAttribute("error", "Client does not exist");
			return "redirect: /clients";
		}
		Bill bill = new Bill();
		bill.setClient(client);
		model.put("bill", bill);
		
		return "bill/form";
	}
	
	@GetMapping("/billDetails/{id}")
	public String getDetails(@PathVariable("id")Long id, Model model, RedirectAttributes flash)
	{
		Bill bill=cs.findBillById(id);
		if(bill == null)
		{
			flash.addFlashAttribute("error","Bill not found");
			return "redirect:/clientDetails";
			
		}
		model.addAttribute("bill", bill);
		model.addAttribute("title","Bill: "+bill.getDescription());
		return "bill/billDetails";
	}
	
	@GetMapping(value="/upload-products/{term}", produces = {"application/json"})
	public @ResponseBody List<Product>uploadProducts(@PathVariable (name="term")String term)
	{
		return cs.findByDescriptionLikeIgnoreCase(term);
	}

	@PostMapping("/form")
	public String save (@Valid Bill bill
			,BindingResult result
			,Model model
			, @RequestParam (name="item_id[]" ,required =false)Long[]itemId
			, @RequestParam(name="quantity[]")Integer []quantity
			, RedirectAttributes flash
			,SessionStatus status
			)
	{
		log.info(result.getAllErrors().toString());
		if(result.hasErrors())
		{
			model.addAttribute("title","Create Bill");
			return "bill/form";
		}
		
		if(itemId==null || itemId.length==0)
		{
			model.addAttribute("title", "Create Bill");
			model.addAttribute("error","Error: At least one item must be selected and added ");
			return "bill/form";
		}
		
		for(int x =0; x < itemId.length; x++)
		{
			Product prod = cs.findProductById(itemId[x]);
			BillItem line = new BillItem();
			line.setQuantity(quantity[x]);
			line.setProduct(prod);
			bill.addItem(line);
			
			log.info("ID: "+itemId[x]+ " Quantity: "+quantity[x]);
		}
		cs.saveBill(bill);
		status.setComplete();
		flash.addFlashAttribute("success", "Bill uploaded successfuly");
		return "redirect:/clientDetails/"+bill.getClient().getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id,RedirectAttributes flash )
	{
		Bill bill = cs.findBillById(id);
		if(bill != null )
		{
			cs.deleteBill(id);
			flash.addFlashAttribute("success","Bill deleted successfuly");
		}
		flash.addFlashAttribute("error","bill not found");
		return"redirect:/clientDetails/"+bill.getClient().getId();
	}
}
