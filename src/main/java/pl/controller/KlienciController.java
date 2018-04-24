package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.model.Klient;
import pl.model.User;
import pl.repository.KlientRepository;

import java.util.List;

@Controller
public class KlienciController {

    private KlientRepository klientRepository;
    CheckAuth user;

    @Autowired
    public void setKlientRepository(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    @GetMapping("/klienci")
	public String register(Model model)
    {
    	user = new CheckAuth(model);
        List<Klient> all=klientRepository.findAll();
        model.addAttribute("all",all);

        return user.checkPracownik("klienci");
    }

    @GetMapping("/klienciAdd")
    public String addKlienta(Model model)
    {
        model.addAttribute("klient",new Klient());
        return user.checkPracownik("addKlient");
    }
    @PostMapping("/addKlient")
    public String addKlient(@ModelAttribute Klient klient,Model model)
    {
    	user = new CheckAuth(model);
        klientRepository.save(klient);
        List<Klient> all=klientRepository.findAll();
        model.addAttribute("all",all);
        model.addAttribute("succes","Dodano nowego Klienta");
        return user.checkPracownik("klienci");
    }

}
