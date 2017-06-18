package it.uniroma3.Galleria.controller;

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

@Controller
public class AutoreController {
	@Autowired 
	private AutoreService autoreService;
	
	@RequestMapping(value="/nuovoAutore",method=RequestMethod.POST)
	public String addAutore(@ModelAttribute Autore autore,Model model){
		autoreService.add(autore);
		model.addAttribute("autori",this.autoreService.findAll());
		model.addAttribute("opera", new Opera());
		return "redirect:/formOpera.html";
	}
	
	@RequestMapping("/formAutore")
	public String getFormAutore(Model model){
		model.addAttribute("autore", new Autore());
		return "formAutore";
	}
	
	@RequestMapping("/autore{id}")
	public String getAutore(@RequestParam Long id,Model model){
		model.addAttribute("user",true);
		model.addAttribute("autore",this.autoreService.findbyId(id));
		return "autore";	
	}
	
	@RequestMapping("/autoreAdmin{id}")
	public String getAutoreAdmin(@RequestParam Long id,Model model){
		model.addAttribute("admin",true);
		model.addAttribute("autore",this.autoreService.findbyId(id));
		return "autore";	
	}
	
	@RequestMapping("/autoriAdmin")
	public String getAutori(Model model){
		model.addAttribute("autori",this.autoreService.findAll());
		return "mostraAutori";
	}
	
	

}
