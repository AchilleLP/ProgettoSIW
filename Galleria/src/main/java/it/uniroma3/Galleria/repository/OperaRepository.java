package it.uniroma3.Galleria.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;


import it.uniroma3.Galleria.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,Long> {
	
	List<Opera> findByTitoloContainingIgnoreCase(String titolo);
	List<Opera> findByDataRealizzazione(int dataRealizzazione);
	List<Opera> findByAutoreNomeIgnoreCase(String nome);
	List<Opera> findByAutoreCognomeIgnoreCase(String cognome);
	List<Opera> findByAutoreId(Long id);
	List<Opera> removeByAutoreId(Long id);

}