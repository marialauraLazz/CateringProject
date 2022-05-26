package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.validator.BuffetValidator;


@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;
	
	//lo utilizzo per poter selezionare lo chef da associare al buffet
	@Autowired
	private ChefService chefService;
	
	@Autowired 
	private BuffetValidator BuffetValidator;
	
	@GetMapping("/buffet")
	public String getBuffet(Model model) {
		model.addAttribute("buffet", new Buffet());
		
		List<Buffet>buffets=buffetService.findAll();
		model.addAttribute("buffets", buffets);
		
		List<Chef>chefs=chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "buffet/buffetForm.html";
	}
	
	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
		BuffetValidator.validate(buffet, bindingResults);
		if(!bindingResults.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "redirect:/buffet";
		}
		List<Buffet>buffets=buffetService.findAll();
		model.addAttribute("buffets", buffets);

		List<Chef>chefs=chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "buffet/buffetForm.html";
	}
	
	@GetMapping("/buffet/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Buffet buffet=buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffet/buffet.html";
	}
	
	@GetMapping("/toDeleteBuffet/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		buffetService.removeById(id);
		return "redirect:/buffet";
	}
	
}
