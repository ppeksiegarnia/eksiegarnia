package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.model.Produkty;
import pl.repository.ProduktyRepository;

@Controller
public class FilterBooksEbooks {

    @Autowired
    private ProduktyRepository produktyRepository;


    @GetMapping("/kategoriaEBooks")
    public String kategoriaEBooksa(Model model, @RequestParam(defaultValue ="Wszystkie") String word,  @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue ="Trafność") String filter)
    {


        Page<Produkty> produktyPage=null;
        if(word.equals("Wszystkie")) {
            if(filter.equals("Trafność"))
                produktyPage = produktyRepository.findAllByCzyEbook(true, new PageRequest(page, 12));
            if(filter.equals("Cena rosnąco"))
                produktyPage=produktyRepository.findAllByCzyEbookOrderByCenaBruttoAsc(true,new PageRequest(page,12));
            if(filter.equals("Cena malejąco"))
                produktyPage=produktyRepository.findAllByCzyEbookOrderByCenaBruttoDesc(true,new PageRequest(page,12));
        }

        else
        {
            if(filter.equals("Trafność"))
                produktyPage = produktyRepository.findAllByKategoriaAndCzyEbook(word, true, new PageRequest(page, 12));
            if(filter.equals("Cena rosnąco"))
                produktyPage = produktyRepository.findAllByKategoriaAndCzyEbookOrderByCenaBruttoAsc(word, true, new PageRequest(page, 12));
            if(filter.equals("Cena malejąco"))
                produktyPage=produktyRepository.findAllByKategoriaAndCzyEbookOrderByCenaBruttoDesc(word,true,new PageRequest(page,12));

        }



        int tab[] = new int[produktyPage.getTotalPages()];
        for (int i = 0; i < produktyPage.getTotalPages(); i++) {
            tab[i] = i;

        }


        model.addAttribute("filter",filter);
        model.addAttribute("word",word);
        model.addAttribute("ile",tab);
        model.addAttribute("ebookss",true);
        model.addAttribute("products",produktyPage);
        new CheckAuth(model);
        return "ebooks";
    }




    @GetMapping("/kategoriaBooks")
    public String kategoriaBooksa(Model model, @RequestParam(defaultValue ="Wszystkie") String word,  @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue ="Trafność") String filter)
    {


        Page<Produkty> produktyPage=null;
        if(word.equals("Wszystkie")) {
            if(filter.equals("Trafność"))
                produktyPage = produktyRepository.findAllByCzyEbook(false, new PageRequest(page, 12));
            if(filter.equals("Cena rosnąco"))
                produktyPage=produktyRepository.findAllByCzyEbookOrderByCenaBruttoAsc(false,new PageRequest(page,12));
            if(filter.equals("Cena malejąco"))
                produktyPage=produktyRepository.findAllByCzyEbookOrderByCenaBruttoDesc(false,new PageRequest(page,12));
        }

        else
        {
            if(filter.equals("Trafność"))
                produktyPage = produktyRepository.findAllByKategoriaAndCzyEbook(word, false, new PageRequest(page, 12));
            if(filter.equals("Cena rosnąco"))
                produktyPage = produktyRepository.findAllByKategoriaAndCzyEbookOrderByCenaBruttoAsc(word, false, new PageRequest(page, 12));
            if(filter.equals("Cena malejąco"))
                produktyPage=produktyRepository.findAllByKategoriaAndCzyEbookOrderByCenaBruttoDesc(word,false,new PageRequest(page,12));

        }



        int tab[] = new int[produktyPage.getTotalPages()];
        for (int i = 0; i < produktyPage.getTotalPages(); i++) {
            tab[i] = i;

        }

        model.addAttribute("filter",filter);
        model.addAttribute("word",word);
        model.addAttribute("ile",tab);
        model.addAttribute("ebookss",true);
        model.addAttribute("products",produktyPage);
        new CheckAuth(model);
        return "books";
    }



}

