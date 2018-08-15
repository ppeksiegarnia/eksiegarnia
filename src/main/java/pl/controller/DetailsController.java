package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import pl.model.Produkty;
import pl.model.User;
import pl.repository.ProduktyRepository;

@Controller
public class DetailsController {

    private ProduktyRepository produktyRepository;


    @Autowired
    public void setProduktyRepository(ProduktyRepository produktyRepository) {
        this.produktyRepository = produktyRepository;
    }

    @GetMapping("/details")
	public String register(Model model, @RequestParam Long ID)
    {
        Produkty produkty=produktyRepository.getOne(ID);

        model.addAttribute("produkt",produkty);
		new CheckAuth(model);
        return "details";
    }
}
