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

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.validator.ChefValidator;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@Autowired 
	private ChefValidator chefValidator;
	
	@GetMapping("/chefs")
	public String getChef(Model model) {
		model.addAttribute("chef", new Chef());
		List<Chef>chefs=chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}
	
	@PostMapping("/chefs")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		chefValidator.validate(chef, bindingResults);
		if(!bindingResults.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "redirect:/chefs";
		}
		return "chefs.html";
	}
	
	@GetMapping("/toDeleteChef/{id}")
	public String deletePersona(@PathVariable("id") Long id, Model model) {
		chefService.removeById(id);
		return "redirect:/chefs";
	}
	
}
