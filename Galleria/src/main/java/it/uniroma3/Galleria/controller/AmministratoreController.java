package it.uniroma3.Galleria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.Galleria.model.Amministratore;
import it.uniroma3.Galleria.service.AmministratoreService;

@Controller
public class AmministratoreController {
	
	@Autowired
	private AmministratoreService amministratoreService;
	
	@RequestMapping(value="/admin",method=RequestMethod.POST)
	public String addAdmin(@Valid @ModelAttribute Amministratore admin,BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("admin",admin);
			model.addAttribute("error",true);
		}
		else{ 
			amministratoreService.addAmministratore(admin);
			model.addAttribute("newadmin", true);
		}
		return this.getAdmin(model);
	}
	

	@RequestMapping("/admin")
	public String getAdmin(Model model){
		model.addAttribute("amministratori", this.amministratoreService.findAll());
		if(!model.containsAttribute("admin"))
			model.addAttribute("admin", new Amministratore());
		return "mostraAmministratori";
	}
	
	//metodo per eliminare amministratore
	@RequestMapping(value="/rimuoviAdmin{id}",method=RequestMethod.DELETE)
	public String removeAdmin(@RequestParam Long id,Model model){
		this.amministratoreService.delete(id);
		System.out.println(id);
		return this.getAdmin(model);
	}
}
