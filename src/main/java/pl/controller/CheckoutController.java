package pl.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.model.Pracownik;
import pl.model.Produkty;
import pl.model.User;
import pl.repository.ProduktyRepository;
import pl.repository.UserRepository;

@Controller
public class CheckoutController {

	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private ProduktyRepository produktRepository;
	
    @GetMapping("/checkout")
    public String home(Model model)
    {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user=UserRepository.findFirstByEmail(auth.getName());
    	model.addAttribute("userss",user.getProductSet());
    	double suma=0;
    	for(Produkty p: user.getProductSet()) {
    		suma+=p.getCenaBrutto();
    	}
    	double koszt=10.0;
    	model.addAttribute("suma",suma);
    	model.addAttribute("koszt",koszt);
    	double sumaAll=suma+koszt;
    	model.addAttribute("sumaAll",sumaAll);
        new CheckAuth(model);
        model.addAttribute("productss",user.getProductSet());
        return "checkout";
    }
    
    
    @PostMapping("/wyborDostawy")
    public String wybor(Model model,@RequestParam String filter) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user=UserRepository.findFirstByEmail(auth.getName());
    	model.addAttribute("userss",user.getProductSet());
    	double suma=0;
    	for(Produkty p: user.getProductSet()) {
    		suma+=p.getCenaBrutto();
    	}
    	double koszt=0;
    	if(filter.equals("Poczta polska"))
    	{
    		System.out.println("p");
    		koszt=10;
    	}
    	else if(filter.equals("Odbior osobisty")) {
    		System.out.println("odb");
    		koszt=0;
    	}
    	else {
    		System.out.println("elce");
    		koszt=30;
    	}
    	model.addAttribute("suma",suma);
    	model.addAttribute("koszt",koszt);
    	double sumaAll=suma+koszt;
    	model.addAttribute("sumaAll",sumaAll);
    	 model.addAttribute("productss",user.getProductSet());
    	 new CheckAuth(model);
         return "checkout";
    }
    
    @GetMapping("/UsunKoszyk") 
    public String usun(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user=UserRepository.findFirstByEmail(auth.getName());
    	
    	UserRepository.deleteBooks(user.getID());
    	new CheckAuth(model);
    	return "redirect:checkout";
    }
    
    @GetMapping("/UsunKsiazke") 
    public String usunKsiazke(Model model, @RequestParam Long ID) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user=UserRepository.findFirstByEmail(auth.getName());
    	
    	UserRepository.deleteBook(user.getID(), ID);
    	new CheckAuth(model);
    	model.addAttribute("userss",user.getProductSet());
    	model.addAttribute("productss",user.getProductSet());
    	return "redirect:checkout";
    }
  
    
    
    
    
}
