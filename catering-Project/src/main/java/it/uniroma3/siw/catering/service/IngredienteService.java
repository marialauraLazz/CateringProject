package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredienti=new ArrayList<>();
		for(Ingrediente ingrediente: ingredienteRepository.findAll()) {
			ingredienti.add(ingrediente);
		}
		return ingredienti;
	}
	
	public boolean alreadyExists(Ingrediente  ingrediente) {
		return ingredienteRepository.existsByNomeAndDescrizioneAndOrigine(ingrediente.getNome(), ingrediente.getDescrizione(), ingrediente.getOrigine());
	}

	public void removeById(Long id) {
		ingredienteRepository.deleteById(id);
	}

}
