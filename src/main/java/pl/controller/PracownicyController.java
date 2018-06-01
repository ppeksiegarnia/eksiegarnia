package pl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.model.Pracownik;
import pl.model.User;
import pl.repository.PracownicyRepository;
import pl.repository.UserRepository;

import java.util.List;

@Controller
public class PracownicyController {

    private PracownicyRepository pracownicyRepository;
    private UserRepository userRepository;
    CheckAuth user;

    @Autowired
    public void setPracownicyRepository(PracownicyRepository pracownicyRepository) {
        this.pracownicyRepository = pracownicyRepository;
    }
    
    @Autowired
    public void setKlientRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@GetMapping("/pracownicy")
	public String register(Model model)
    {
		user = new CheckAuth(model);

		List<Pracownik> all=pracownicyRepository.findAll();
		model.addAttribute("all",all);
		return user.checkAdmin("pracownicy");
    }

    @GetMapping("/add")
    public String add(Model model)
    {
    	User u = new User();
        Pracownik pracownik=new Pracownik(u);
        model.addAttribute("pracownik",pracownik);
        
        return user.checkAdmin("addPracownicy");
    }

    @PostMapping("/addPracownicy")
    public String addPracownicy(@ModelAttribute Pracownik pracownik,Model model)
    {
    	user = new CheckAuth(model);
    	pracownik.setPensja(2.0);
        pracownicyRepository.save(pracownik);
        List<Pracownik> all=pracownicyRepository.findAll();
        model.addAttribute("all",all);
        model.addAttribute("succes","Dodano nowego pracownika");
        return user.checkAdmin("pracownicy");
    }
}
