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
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;
import it.uniroma3.siw.catering.validator.PiattoValidator;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;

	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired 
	private PiattoValidator piattoValidator;

	@GetMapping("/admin/piatto")
	public String getPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());

		List<Piatto>piatti=piattoService.findAll();
		model.addAttribute("piatti", piatti);
		
		List<Buffet>buffets=buffetService.findAll();
		model.addAttribute("buffets", buffets);
		
		List<Ingrediente>ingredienti=ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "piatto/piattoForm.html";
	}

	@PostMapping("/admin/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		piattoValidator.validate(piatto, bindingResults);
		if(!bindingResults.hasErrors()) {
			piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "redirect:/admin/piatto";
		}
		List<Piatto>piatti=piattoService.findAll();
		model.addAttribute("piatti", piatti);

		List<Buffet>buffets=buffetService.findAll();
		model.addAttribute("buffets", buffets);
		
		List<Ingrediente>ingredienti=ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "piatto/piattoForm.html";
	}
	
	@GetMapping("/piattoUtente")
	public String getPiatti(Model model) {
		List<Piatto>piatti=piattoService.findAll();
		model.addAttribute("piatti", piatti);
		
		List<Buffet>buffets=buffetService.findAll();
		model.addAttribute("buffets", buffets);
		
		List<Ingrediente>ingredienti=ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "piatto/piattoElenco.html";
	}
	
	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto=piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "piatto/piatto.html";
	}
	
	@GetMapping("/admin/piatto/{id}")
	public String getPiattoperAdmin(@PathVariable("id") Long id, Model model) {
		Piatto piatto=piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "piatto/caratteristichePiatto.html";
	}

	@GetMapping("/admin/toDeletePiatto/{id}")
	public String deletePiatto(@PathVariable("id") Long id, Model model) {
		piattoService.removeById(id);
		return "redirect:/admin/piatto";
	}

}
