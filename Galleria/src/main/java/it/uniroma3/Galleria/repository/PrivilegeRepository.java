package it.uniroma3.Galleria.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.Galleria.model.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long>{

	public Privilege findByName(String name);
}
