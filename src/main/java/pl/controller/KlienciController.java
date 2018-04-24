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

    @Autowired
    public void setKlientRepository(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    @GetMapping("/klienci")
	public String register(Model model)
    {
        List<Klient> all=klientRepository.findAll();
        model.addAttribute("all",all);

		new CheckAuth(model);
        return "klienci";
    }

    @GetMapping("/klienciAdd")
    public String addKlienta(Model model)
    {
        model.addAttribute("klient",new Klient());
        return "addKlient";
    }
    @PostMapping("/addKlient")
    public String addKlient(@ModelAttribute Klient klient,Model model)
    {
    	
    	System.err.println("ccccccc"+klient);
        klientRepository.save(klient);
        List<Klient> all=klientRepository.findAll();
        model.addAttribute("all",all);
        model.addAttribute("succes","Dodano noweg Klienta");
        return "klienci";
    }

}
