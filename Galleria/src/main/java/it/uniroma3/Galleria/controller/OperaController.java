package it.uniroma3.Galleria.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.Galleria.model.Autore;

import it.uniroma3.Galleria.model.Opera;
import it.uniroma3.Galleria.service.AutoreService;
import it.uniroma3.Galleria.service.OperaService;

@Controller
public class OperaController {
	@Autowired
	private OperaService operaService;
	@Autowired
	private AutoreService autoreService;
	
	//metodo per ottenere tutte le opere da utente generico
	@RequestMapping("/opere")
	public String getOpere(Model model){
		model.addAttribute("opere",operaService.findAll());
		model.addAttribute("user",true);
		return "mostraOpere";
	}
	
	//metodo per ottenere tutte le opere da amministratore
	@RequestMapping("/opereadmin")
	public String getOpereAdmin(Model model){
		model.addAttribute("opere",operaService.findAll());
		model.addAttribute("admin", true);
		return "mostraOpere";
	}
	
	//metodo per aggiungere opera
	@RequestMapping(value="/opere",method=RequestMethod.POST)
	public String addOpera(@Valid @ModelAttribute Opera opera,Model model, BindingResult result){
		String nextPage;
		if(opera.getAutore()==null){
			model.addAttribute("autore",new Autore());
			model.addAttribute("opera", opera);
			nextPage="formAutore";
		}
		else if(result.hasErrors()){
			model.addAttribute("opera", opera);
			nextPage="formOpera";
		}
		else{
			operaService.add(opera);
			nextPage="/adminPage";
			model.addAttribute("inserita",true);
		}

		return nextPage;
	}
	
	//metodo per modificare un opera inserita
	@RequestMapping(value="/opera{id}",method=RequestMethod.PUT)
	public void updateOpera(@RequestParam Long id,Opera opera){
		//Da implementare
		//operaService.update(id, opera);
	}
	
	//metodo per cancellare opera
	@RequestMapping(value="/opera{id}",method=RequestMethod.DELETE)
	public String deleteOpera(@RequestParam Long id, Model model){
		operaService.delete(id);
		return this.getOpereAdmin(model);
	}
	
	//metodo per ricercare opera in base a titolo/anno/nome o cognome autore
	@RequestMapping(value = "/ricercaOpere")
	public String ricerca(@RequestParam("tipoRicerca") String tipoRicerca,@RequestParam ("ricerca") String ricerca,Model model){
		List<Opera> opere;
		switch(tipoRicerca){
			case "autore" :
				if(elaboraRicerca(ricerca).equals("autoreNome"))		
					opere = this.operaService.findByAutoreNome(ricerca);
				else
					opere = this.operaService.findByAutoreCognome(ricerca);
				break;
			case "operaTitolo" : opere = this.operaService.findByTitolo(ricerca);
				break;
			case "operaAnno" : 
				if(this.isInt(ricerca))
					opere = this.operaService.findByDataRealizzazione(Integer.parseInt(ricerca));
				else
					opere = new ArrayList<>();
				break;
			default: opere = new ArrayList<>();
				break;
		}
		model.addAttribute("opere",opere);
		model.addAttribute("ricerca",ricerca);
		return "risultatiRicerca";
	}
	
	private String elaboraRicerca(String ricerca){
		String tipoRicerca;
		if(!autoreService.findByNome(ricerca).isEmpty())
			tipoRicerca = "autoreNome";
		else
			tipoRicerca = "autoreCognome";
		
		return tipoRicerca;
	}
	
	//Ricerca per autore nome cognome da implementare
	/*private Long elaboraRicercaNomeCognome(String ricerca){
		int indiceSpazio = ricerca.indexOf(' ');
		if(indiceSpazio >0 && indiceSpazio<ricerca.length()-1){
			String nome = ricerca.substring(0,indiceSpazio);
			String cognome = ricerca.substring(indiceSpazio+1);
			Autore autoreCercato = autoreService.findByNomeCognome(nome, cognome);
			if(autoreCercato!=null)
				return autoreCercato.getId();
		}
		return -1L;
	}*/
	
	
	private boolean isInt(String str) {
		try{
		Integer.parseInt(str);
		return true;
		}
		catch(NumberFormatException e) { return false; }
	}
	
	//metodo per eliminare autore
	@RequestMapping(value="/autore{id}",method=RequestMethod.DELETE)
	public String deleteAutore(@RequestParam Long id, Model model){
		this.operaService.removeByAutoreId(id);
		this.autoreService.delete(id);
		model.addAttribute("autori",autoreService.findAll());
		return "mostraAutori";
	}

	

}
