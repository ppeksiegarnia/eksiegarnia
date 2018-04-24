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

import java.util.List;

@Controller
public class PracownicyController {

    private PracownicyRepository pracownicyRepository;


    @Autowired
    public void setPracownicyRepository(PracownicyRepository pracownicyRepository) {
        this.pracownicyRepository = pracownicyRepository;
    }

    private static final Logger log= LoggerFactory.getLogger(PracownicyController.class);

	@GetMapping("/pracownicy")
	public String register(Model model)
    {
		new CheckAuth(model);

		List<Pracownik> all=pracownicyRepository.findAll();
		model.addAttribute("all",all);
		CheckAuth user = new CheckAuth(model);
		return user.checkAdmin("pracownicy");
    }

    @GetMapping("/add")
    public String add(Model model)
    {
        Pracownik pracownik=new Pracownik();
        model.addAttribute("pracownik",pracownik);

        return "addPracownicy";
    }

    @PostMapping("/add")
    public String dodaj(@ModelAttribute Pracownik pracownik,Model model)
    {
        pracownicyRepository.save(pracownik);
        List<Pracownik> all=pracownicyRepository.findAll();
        model.addAttribute("all",all);
        model.addAttribute("succes","Dodano nowego praocownika");
        return "pracownicy";
    }
}
