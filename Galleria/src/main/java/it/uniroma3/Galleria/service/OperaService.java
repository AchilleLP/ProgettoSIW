package it.uniroma3.Galleria.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.Galleria.model.Opera;
import it.uniroma3.Galleria.repository.OperaRepository;



@Service
public class OperaService {
	  @Autowired
	    private OperaRepository operaRepository; 

	    public List<Opera> findAll() {
	    	List<Opera> opere = new ArrayList<>();
	    	opere.addAll((Collection<? extends Opera>) this.operaRepository.findAll());
	        return opere;
	    }

	    @Transactional
	    public void add(final Opera opera) {
	        this.operaRepository.save(opera);
	    }
	    
	    @Transactional
		public Opera findbyId(Long id) {
			return this.operaRepository.findOne(id);
		}
	    
		@Transactional
		public void delete(Long id){
			this.operaRepository.delete(id);
		}
		
		//da implementare
		public void update(Long id,Opera opera){
			this.operaRepository.save(opera);
		}
		
		@Transactional
		public List<Opera> findByTitolo(String titolo){
			return this.operaRepository.findByTitoloContainingIgnoreCase(titolo);
		}
		
		@Transactional
		public List<Opera> findByDataRealizzazione(int anno){
			return this.operaRepository.findByDataRealizzazione(anno);
		}
		
		@Transactional
		public List<Opera> findByAutoreNome(String nome){
			return this.operaRepository.findByAutoreNomeIgnoreCase(nome);
		}
		
		@Transactional
		public List<Opera> findByAutoreCognome(String cognome){
			return this.operaRepository.findByAutoreCognomeIgnoreCase(cognome);
		}
	
		@Transactional
		public List<Opera> findByAutoreId(Long id){
			return this.operaRepository.findByAutoreId(id);
		}
		
		@Transactional
		public List<Opera> removeByAutoreId(Long id){
			return this.operaRepository.removeByAutoreId(id);
		}
		
		
}

