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
import pl.repository.UserRepository;

import java.util.List;

@Controller
public class KlienciController {

    private UserRepository userRepository;
    CheckAuth user;

    @Autowired
    public void setKlientRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/klienci")
	public String register(Model model)
    {
    	user = new CheckAuth(model);
    	List<User> all=userRepository.findAllUsers();
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
    public String addKlient(@ModelAttribute User klient,Model model)
    {
    	user = new CheckAuth(model);
    	userRepository.save(klient);
        List<User> all=userRepository.findAllUsers();
        model.addAttribute("all",all);
        model.addAttribute("succes","Dodano nowego Klienta");
        return user.checkPracownik("klienci");
    }

}
