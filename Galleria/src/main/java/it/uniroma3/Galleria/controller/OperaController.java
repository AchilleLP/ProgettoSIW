package it.uniroma3.Galleria.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/opere")
	public String getOpere(Model model){
		model.addAttribute("opere",operaService.findAll());
		model.addAttribute("user",true);
		return "mostraOpere";
	}
	
	@RequestMapping("/opereadmin")
	public String getOpereAdmin(Model model){
		model.addAttribute("opere",operaService.findAll());
		model.addAttribute("admin", true);
		return "mostraOpere";
	}
	
	
	@RequestMapping("/opere/{id}")
	//per collegare {id} con id del metodo basta aggiungere @PathVariable
	//se il la variabile del metodo aveva un nome diverso era necessario aggiungere
	//@PathVariable("id")
	public String getOpera(@PathVariable Long id,Model model){
		model.addAttribute("opera",operaService.findbyId(id));
		return "opera";
	}
	
	
	@RequestMapping(value="/opere",method=RequestMethod.POST)
	public String addOpera(@ModelAttribute Opera opera,Model model){
		String nextPage;
		
		if(opera.getAutore() == null){
			model.addAttribute("autore", new Autore());
			nextPage="formAutore";
		}
		else{
			operaService.add(opera);
			nextPage="/adminPage";
			model.addAttribute("inserita",true);
		}

		return nextPage;
	}
	
	//metodo per modificare un opera inserita
	@RequestMapping(value="/opere/{id}",method=RequestMethod.PUT)
	public void updateOpera(@PathVariable Long id,Opera opera){
		//Da implementare
		//operaService.update(id, opera);
	}
	
	//metodo per cancellare opera
	@RequestMapping(value="/opere/{id}",method=RequestMethod.DELETE)
	public String deleteOpera(@PathVariable Long id, Model model){
		operaService.delete(id);
		model.addAttribute("delete", true);
		return "adminPage";
	}
	
	@RequestMapping(value = "/ricercaOpere")
	public String ricerca(@RequestParam("tipoRicerca") String tipoRicerca,@RequestParam ("ricerca") String ricerca,Model model){
		List<Opera> opere;
		switch(tipoRicerca){
			case "autore" :
				if(elaboraRicerca(ricerca).equals("autoreNome"))		
					opere = this.operaService.findByAutoreNome(ricerca);
				else // if(elaboraRicerca(ricerca).equals("autoreCognome"))
					opere = this.operaService.findByAutoreCognome(ricerca);
				/*else{
					if(elaboraRicercaNomeCognome(ricerca)>-1)
						opere = this.operaService.findByAutoreId(elaboraRicercaNomeCognome(ricerca));
					else 
						opere = new ArrayList<>();		
				}*/	
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
	
	@RequestMapping(value="/autori/{id}",method=RequestMethod.DELETE)
	public String deleteAutore(@PathVariable Long id, Model model){
		this.operaService.removeByAutoreId(id);
		this.autoreService.delete(id);
		model.addAttribute("deleteAutore",true);
		return "redirect:/adminPage.html";
	}

	

}
