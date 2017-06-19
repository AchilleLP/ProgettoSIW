package it.uniroma3.Galleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.Galleria.model.Autore;
import it.uniroma3.Galleria.model.Opera;
import it.uniroma3.Galleria.service.AutoreService;

@Controller
public class AutoreController {
	@Autowired 
	private AutoreService autoreService;
	
	//metodo per aggiungere autore
	@RequestMapping(value="/nuovoAutore",method=RequestMethod.POST)
	public String addAutore(@ModelAttribute Autore autore, BindingResult result,Model model){
		if(result.hasErrors()){
			model.addAttribute("autore",autore);
			return this.getFormAutore(model);
		}
		else{
			autoreService.add(autore);
			model.addAttribute("opera", new Opera());
			model.addAttribute("autori",this.autoreService.findAll());
			return "formOpera";
		}
	}

	@RequestMapping("/formAutore")
	public String getFormAutore(Model model){
		if(!model.containsAttribute("autore"))
			model.addAttribute("autore", new Autore());
		return "formAutore";
	}
	
	//metodo per visualizzare autore da utenteGenerico
	@RequestMapping("/autore{id}")
	public String getAutore(@RequestParam Long id,Model model){
		model.addAttribute("user",true);
		model.addAttribute("autore",this.autoreService.findbyId(id));
		return "autore";	
	}
	
	//metodo per visualizzare autore da admin
	@RequestMapping("/autoreAdmin{id}")
	public String getAutoreAdmin(@RequestParam Long id,Model model){
		model.addAttribute("admin",true);
		model.addAttribute("autore",this.autoreService.findbyId(id));
		return "autore";	
	}
	
	//metodo per visualizzare tutti gli autori
	@RequestMapping("/autoriAdmin")
	public String getAutori(Model model){
		model.addAttribute("autori",this.autoreService.findAll());
		return "mostraAutori";
	}
	
	//il metodo per cancellare autore è in operaController
	
	

}
