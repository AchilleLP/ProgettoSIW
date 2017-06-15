package it.uniroma3.Galleria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.Galleria.model.Autore;
import it.uniroma3.Galleria.repository.AutoreRepository;


@Service
public class AutoreSevice {
	@Autowired
    private AutoreRepository autoreRepository; 

    public List<Autore> findAll() {
    	List<Autore> autori = new ArrayList<>();
    	autori.addAll((Collection<? extends Autore>) this.autoreRepository.findAll());
        return autori;
    }

    @Transactional
    public void add(final Autore autore) {
        this.autoreRepository.save(autore);
    }
    
    @Transactional
	public Autore findbyId(Long id) {
		return this.autoreRepository.findOne(id);
	}
    
	@Transactional
	public void delete(Long id){
		this.autoreRepository.delete(id);
	}

}
