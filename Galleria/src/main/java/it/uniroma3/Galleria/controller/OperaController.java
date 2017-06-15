package it.uniroma3.Galleria.controller;

<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/AchilleLP/ProgettoSIW.git
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD

=======
import it.uniroma3.Galleria.model.Autore;
>>>>>>> branch 'master' of https://github.com/AchilleLP/ProgettoSIW.git
import it.uniroma3.Galleria.model.Opera;
import it.uniroma3.Galleria.service.OperaService;

@Controller
public class OperaController {
	@Autowired
	private OperaService operaService;
	
	@RequestMapping("/opere")
	public String getOpere(Model model){
		model.addAttribute("opere",operaService.findAll());
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
			nextPage="redirect:/index.html";
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
	public String deleteOpera(@PathVariable Long id){
		operaService.delete(id);
		return "redirect:/index.html";
	}
	

	

}
