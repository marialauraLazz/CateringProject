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
	
	@GetMapping("/chefUtente")
	public String getChefs(Model model) {
		List<Chef>chefs=chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef/chefElenco.html";
	}
	
	@GetMapping("/admin/chefs")
	public String getChef(Model model) {
		model.addAttribute("chef", new Chef());
		List<Chef>chefs=chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef/chefForm.html";
	}
	
	@PostMapping("/admin/chefs")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		chefValidator.validate(chef, bindingResults);
		if(!bindingResults.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "redirect:/admin/chefs";
		}
		List<Chef>chefs=chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef/chefForm.html";
	}
	
	@GetMapping("/chef/{id}")
	public String getchef(@PathVariable("id") Long id, Model model) {
		Chef chef=chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef/chef.html";
	}
	
	@GetMapping("/admin/chef/{id}")
	public String getchefperAdmin(@PathVariable("id") Long id, Model model) {
		Chef chef=chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef/caratteristicheChef.html";
	}
	
	@GetMapping("/admin/toDeleteChef/{id}")
	public String deletechef(@PathVariable("id") Long id, Model model) {
		chefService.removeById(id);
		return "redirect:/admin/chefs";
	}
	
	@GetMapping("/admin/toEditChef/{id}")
	public String getChefEdited(@PathVariable("id") Long id, Model model) {
		Chef chef= chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef/editChef.html";
	}
	
	@PostMapping("/admin/toEditChef/{id}")
	public String editChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, @PathVariable("id") Long id, Model model) {
		chefValidator.validate(chef, bindingResults);
		if(!bindingResults.hasErrors()) {
			chefService.save(chef);
			return "redirect:/admin/chefs";
		}
		return "chef/editChef.html";
	}
	
}
