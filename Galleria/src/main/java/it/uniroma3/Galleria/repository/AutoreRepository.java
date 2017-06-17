package it.uniroma3.Galleria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.Galleria.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore,Long> {
	List<Autore> findByNomeIgnoreCase(String nome);
	List<Autore> findByCognomeIgnoreCase(String cognome);
	Autore findByNomeAndCognomeIgnoreCase(String nome,String cognome);
}
