package it.uniroma3.Galleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.Galleria.model.Opera;
@Repository
public interface OperaRepository extends CrudRepository<Opera,Long> {
	@Query("select o from Opera o where o.titolo = ?1")
	List<Opera> findByTitolo(String titolo);
}
