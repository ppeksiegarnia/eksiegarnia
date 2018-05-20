package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.model.Produkty;
import pl.repository.ProduktyRepository;

@Controller
public class AllBooksAndEBook {

    private ProduktyRepository produktyRepository;

    @Autowired
    public void setProduktyRepository(ProduktyRepository produktyRepository) {
        this.produktyRepository = produktyRepository;
    }

    @GetMapping("/search")
    public String search(Model model,@RequestParam(defaultValue ="") String word,@RequestParam(defaultValue = "0") int page)
    {

        Page<Produkty> produktyPage;

        if(word.equals(""))
        {
            produktyPage=produktyRepository.findAll(new PageRequest(page,12));
        }
        else
        {
            produktyPage=produktyRepository.findAllByTytulContaining(word,new PageRequest(page,12));
        }





        int tab[] = new int[produktyPage.getTotalPages()];
        for (int i = 0; i < produktyPage.getTotalPages(); i++) {
            tab[i] = i;

        }
        model.addAttribute("word",word);
        model.addAttribute("ile",tab);
        model.addAttribute("products",produktyPage);

        new CheckAuth(model);
        return "allBooksandEBooks";
    }





}
