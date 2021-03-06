 package it.uniroma3.Galleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.Galleria.model.Amministratore;


@Controller
public class MainController {
	
	
	 // Login form
	  @GetMapping("/login")
	  public String login(Model model) {
	    return "login";
	  }
	  
	  // Login form
	  @RequestMapping("/adminPage")
	  public String joinAdmin(Model model) {
	    return "adminPage";
	  }
	  	  
	  //index
	  @RequestMapping("/")
	  public String getIndex(Model model) {
		model.addAttribute("autenticato", "autenticato");
	    return "redirect:index.html";
	  }
	  
	  // Login form with error
	  @RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login";
	  }
	  
	  
	  //logout
	  @RequestMapping("/logout")
	  public String logout(Model model) {
	    model.addAttribute("logout", true);
	    return "logout";
	  }
	  
	  
	  @RequestMapping("/formAmministratore")
	  public String formAmministratore(Model model){
		  model.addAttribute("admin", new Amministratore());
		  return "formAmministratore";
	  }
	  
}
