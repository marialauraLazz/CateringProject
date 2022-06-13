package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public void save(Chef chef) {
		chefRepository.save(chef);
	}
	
	@Transactional
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}
	
	@Transactional
	public List<Chef> findAll(){
		List<Chef> chefs=new ArrayList<>();
		for(Chef chef: chefRepository.findAll()) {
			chefs.add(chef);
		}
		return chefs;
	}

	@Transactional
	public boolean alreadyExists(Chef chef) {
		return chefRepository.existsByNomeAndCognomeAndNazionalita(chef.getNome(), chef.getCognome(), chef.getNazionalita());
	}

	@Transactional
	public void removeById(Long id) {
		chefRepository.deleteById(id);
	}

}
