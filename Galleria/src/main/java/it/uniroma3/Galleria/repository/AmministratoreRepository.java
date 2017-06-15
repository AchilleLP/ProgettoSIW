package it.uniroma3.Galleria.repository;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.Galleria.model.Amministratore;
import it.uniroma3.Galleria.model.Role;


public interface AmministratoreRepository extends CrudRepository<Amministratore,Long>{
	
	public Amministratore findByNickname(String nickname);

}
