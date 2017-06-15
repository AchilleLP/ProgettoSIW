
package it.uniroma3.Galleria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.Galleria.model.Amministratore;
import it.uniroma3.Galleria.repository.AmministratoreRepository;
import it.uniroma3.Galleria.repository.RoleRepository;



@Service
public class AmministratoreService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private AmministratoreRepository amministratoreRepository;
	
	@Transactional
	public void addAmministratore(final Amministratore admin){
		String pswCript=passwordEncoder.encode(admin.getPassword());
		admin.setPassword(pswCript);
		admin.setRole(roleRepository.findByName("ROLE_ADMIN"));
		amministratoreRepository.save(admin);
	}
	
	@Transactional
	public void deleteAmministratore(final Amministratore admin){
		amministratoreRepository.delete(admin);
	}
	
	public List<Amministratore> findAll(){
		List<Amministratore> amministratori = new ArrayList<>();
		amministratori.addAll((Collection<? extends Amministratore>) this.amministratoreRepository.findAll());
		return amministratori;
	}
	
}
