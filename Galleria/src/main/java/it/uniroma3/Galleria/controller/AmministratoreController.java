package it.uniroma3.Galleria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.Galleria.model.Amministratore;
import it.uniroma3.Galleria.service.AmministratoreService;

@Controller
public class AmministratoreController {
	
	@Autowired
	private AmministratoreService amministratoreService;
	
	@RequestMapping(value="/admin",method=RequestMethod.POST)
	public String addAdmin(@Valid @ModelAttribute Amministratore admin, Model model,BindingResult result){
		if(result.hasErrors()){
			model.addAttribute("admin",admin);
			return "formAmministratore";
		}
		amministratoreService.addAmministratore(admin);
		model.addAttribute("newadmin", true);
		return "adminPage";
	}
	@RequestMapping("/admin")
	public String getAdmin(Model model){
		model.addAttribute("amministratori", this.amministratoreService.findAll());
		return "mostraAmministratori";
	}
	
}
