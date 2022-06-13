package it.uniroma3.siw.catering.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Transactional
	public void save(Buffet buffet) {
		buffetRepository.save(buffet);
	}
	
	@Transactional
	public Buffet findById(Long id) {
		return buffetRepository.findById(id).get();
	}
	
	@Transactional
	public List<Buffet> findAll(){
		List<Buffet> buffets=new ArrayList<>();
		for(Buffet buffet: buffetRepository.findAll()) {
			buffets.add(buffet);
		}
		return buffets;
	}
	
	@Transactional
	public void removeById(Long id) {
		buffetRepository.deleteById(id);
	}

	@Transactional
	public boolean alreadyExists(Buffet buffet) {
		return buffetRepository.existsByNomeAndDescrizione(buffet.getNome(), buffet.getDescrizione());
	}

}
