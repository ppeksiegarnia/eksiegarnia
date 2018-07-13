package pl.controller;

import java.util.Optional;


import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import pl.model.Produkty;
import pl.model.User;
import pl.repository.ProduktyRepository;
import pl.repository.UserRepository;

@Controller
public class BooksController {


    @Autowired
    private ProduktyRepository produktyRepository;
    
    @Autowired
    private UserRepository UserRepository;


    protected final  Logger log=org.slf4j.LoggerFactory.getLogger(getClass());



	@GetMapping("/books")
	public String register(Model model, @RequestParam(defaultValue = "ksiazki") String word,@RequestParam(defaultValue = "0") int page)
    {
        Page<Produkty> produktyPage=null;



        if(word.equals("ksiazki"))
       produktyPage=produktyRepository.findAllByCzyEbook(false,new PageRequest(page,12));
        else
            produktyPage=produktyRepository.findAllByKategoriaAndCzyEbook(word,false,new PageRequest(page,12));

        model.addAttribute("word",word);
       model.addAttribute("products",produktyPage);





        int tab[] = new int[produktyPage.getTotalPages()];
        for (int i = 0; i < produktyPage.getTotalPages(); i++) {
            tab[i] = i;

        }

        model.addAttribute("ile",tab);
        model.addAttribute("ebookss",false);
		new CheckAuth(model);
        return "books";
    }
	

	@GetMapping("/addKoszykB")
	public String addBook(@RequestParam Long ID) {

		Optional<Produkty> op=produktyRepository.findById(ID);
		Produkty p=op.get();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user=UserRepository.findFirstByEmail(auth.getName());
		
		user.getProductSet().add(p);
		
		UserRepository.save(user);
		
		
		return "redirect:books";
		
	}
	
	
	
	
}
