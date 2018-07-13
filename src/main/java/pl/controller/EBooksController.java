package pl.controller;

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

import java.util.List;
import java.util.Optional;

@Controller
public class EBooksController {

    @Autowired
    private ProduktyRepository produktyRepository;
    
    @Autowired
    private UserRepository UserRepository;

	@GetMapping("/ebooks")
	public String register(Model model, @RequestParam(defaultValue = "Wszystkie") String word,@RequestParam(defaultValue = "0") int page)
    {

        Page<Produkty> produktyPage=null;



        if(word.equals("Wszystkie"))
            produktyPage=produktyRepository.findAllByCzyEbook(true,new PageRequest(page,12));
        else
            produktyPage=produktyRepository.findAllByKategoriaAndCzyEbook(word,true,new PageRequest(page,12));



        int tab[] = new int[produktyPage.getTotalPages()];
        for (int i = 0; i < produktyPage.getTotalPages(); i++) {
            tab[i] = i;

        }


        model.addAttribute("word",word);
        model.addAttribute("ile",tab);
        model.addAttribute("ebookss",true);
        model.addAttribute("products",produktyPage);
		new CheckAuth(model);
        return "ebooks";
    }
	
	
	
	
	@GetMapping("/addKoszykE")
	public String addBook(@RequestParam Long ID) {

		Optional<Produkty> op=produktyRepository.findById(ID);
		Produkty p=op.get();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user=UserRepository.findFirstByEmail(auth.getName());
		user.getProductSet().add(p);
		UserRepository.save(user);
		
		return "redirect:ebooks";
		
	}
}
