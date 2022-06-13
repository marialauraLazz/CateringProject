package it.uniroma3.siw.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long>{

	public boolean existsByNomeAndDescrizione(String nome, String descrizione);
	
	public List<Piatto> findByIngredienti(Ingrediente ingrediente);

}
