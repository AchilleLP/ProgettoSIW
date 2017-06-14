package it.uniroma3.Galleria.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.Galleria.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	public Role findByName(String name);
}
