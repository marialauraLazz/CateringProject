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

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.validator.IngredienteValidator;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired 
	private IngredienteValidator ingredienteValidator;

	@GetMapping("/admin/ingrediente")
	public String getIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());

		List<Ingrediente>ingredienti=ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingrediente/ingredienteForm.html";
	}

	@PostMapping("/admin/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		ingredienteValidator.validate(ingrediente, bindingResults);
		if(!bindingResults.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "redirect:/admin/ingrediente";
		}
		List<Ingrediente>ingredienti=ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingrediente/ingredienteForm.html";
	}
	
	@GetMapping("/ingredienteUtente")
	public String getIngredienti(Model model) {
		List<Ingrediente>ingredienti=ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingrediente/ingredienteElenco.html";
	}

	@GetMapping("/admin/toDeleteIngrediente/{id}")
	public String deleteIngrediente(@PathVariable("id") Long id, Model model) {
		ingredienteService.removeById(id);
		return "redirect:/admin/ingrediente";
	}
}
