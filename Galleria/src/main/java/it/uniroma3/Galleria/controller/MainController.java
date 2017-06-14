package it.uniroma3.Galleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.Galleria.model.Opera;

import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.Galleria.model.Opera;

@Controller
public class MainController {
	
	
	 // Login form
	  @GetMapping("/login")
	  public String login(Model model) {
	    return "login";
	  }
	  
	  // Login form with error
	  @RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login";
	  }
	  
	  // Inserisci Opera
	  @RequestMapping("/formOpera")
	  public String formOpera(Model model) {

		model.addAttribute("opera", new Opera());
	    return "formOpera";
	  }
	  
	  
}
