package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.model.Produkty;
import pl.repository.ProduktyRepository;

import java.util.List;

@Controller
public class ProduktyController {


    private ProduktyRepository produktyRepository;
    CheckAuth user;

    @Autowired
    public void setProduktyRepository(ProduktyRepository produktyRepository) {
        this.produktyRepository = produktyRepository;
    }

    @GetMapping("/produkty")
    public String register(Model model) {
    	user = new CheckAuth(model);

        List<Produkty> all = produktyRepository.findAll();
        model.addAttribute("all", all);

        return user.checkPracownik("produkty");
    }


    @GetMapping("/addProduct")
    public String addProduct(Model model) {

        model.addAttribute("product",new Produkty());
        return user.checkPracownik("addProduct");
    }

    @PostMapping("/adProduct")
    public String addProducts(@ModelAttribute Produkty produkty,Model model){
    	user = new CheckAuth(model);
        System.out.println(produkty);
        produktyRepository.save(produkty);
        List<Produkty> all = produktyRepository.findAll();
        model.addAttribute("all", all);
        model.addAttribute("succes","Dodano nowy proukt");
        return user.checkPracownik("produkty");

    }


}
