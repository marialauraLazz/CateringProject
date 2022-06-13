package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;
	
	@Transactional
	public void save(Piatto piatto) {
		piattoRepository.save(piatto);
	}
	
	@Transactional
	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}
	
	@Transactional
	public List<Piatto> findAll(){
		List<Piatto> piatti=new ArrayList<>();
		for(Piatto piatto: piattoRepository.findAll()) {
			piatti.add(piatto);
		}
		return piatti;
	}
	
	@Transactional
	public boolean alreadyExists(Piatto  piatto) {
		return piattoRepository.existsByNomeAndDescrizione(piatto.getNome(), piatto.getDescrizione());
	}

	@Transactional
	public void removeById(Long id) {
		piattoRepository.deleteById(id);
	}
	
	@Transactional
	public void deleteIngredienteDelPiatto(Ingrediente ingrediente) {
		List<Piatto>piattiConIngrediente=piattoRepository.findByIngredienti(ingrediente);
		for(Piatto piatto : piattiConIngrediente) {
			piatto.getIngredienti().remove(ingrediente);
		}
	}
}
