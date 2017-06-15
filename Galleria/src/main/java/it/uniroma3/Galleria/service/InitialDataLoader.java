package it.uniroma3.Galleria.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.Galleria.model.Amministratore;
import it.uniroma3.Galleria.model.Privilege;
import it.uniroma3.Galleria.model.Role;
import it.uniroma3.Galleria.repository.AmministratoreRepository;
import it.uniroma3.Galleria.repository.PrivilegeRepository;
import it.uniroma3.Galleria.repository.RoleRepository;

@Component
public class InitialDataLoader implements
  ApplicationListener<ContextRefreshedEvent> {
 
    boolean alreadySetup = false;
  
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private PrivilegeRepository privilegeRepository;
 
    @Autowired
    private AmministratoreService amministratoreService;
  
    @Autowired
    private AmministratoreRepository amministratoreRepository;
    
    
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
  
        if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
  
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        createAmministratoreIfNotFound("admin","password");
        alreadySetup = true;
    }
 
    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
  
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    @Transactional
    private Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
  
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
    
    @Transactional 
    private Amministratore createAmministratoreIfNotFound(String nick,String password){
    	Amministratore admin= amministratoreRepository.findByNickname(nick);
 
    	if (admin==null){
    		admin=new Amministratore(nick,password);
    		this.amministratoreService.addAmministratore(admin);
    	}
    	return admin;
    }
    
}
    
    
    