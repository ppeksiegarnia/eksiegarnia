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

import java.util.List;

@Controller
public class EBooksController {

    @Autowired
    private ProduktyRepository produktyRepository;

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
}
