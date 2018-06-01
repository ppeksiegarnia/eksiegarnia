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
import org.springframework.web.bind.annotation.RequestParam;

import pl.model.Pracownik;
import pl.model.User;
import pl.repository.PracownicyRepository;
import pl.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Pracownik pracownik=new Pracownik();
        WorkerUserMerger mfo = new WorkerUserMerger(u,pracownik);
        model.addAttribute("mfo",mfo);
        
        return user.checkAdmin("addPracownicy");
    }

    @PostMapping("/addPracownicy")
    public String addPracownicy(@ModelAttribute WorkerUserMerger mfo,Model model)
    {
    	user = new CheckAuth(model);
    	Pracownik pracownik = mfo.getPracownik();
    	User u = mfo.getUzytkownik();
    	userRepository.save(u);
    	pracownik.setID(u.getID());
        pracownicyRepository.save(pracownik);
        List<Pracownik> all=pracownicyRepository.findAll();
        List<User>allu = userRepository.findAllWorkers();
        List<WorkerUserMerger> wmall = new ArrayList<>();
        for(int i =0 ; i< all.size(); i++) {
        	wmall.add(new WorkerUserMerger(allu.get(i),all.get(i)));
        }
        model.addAttribute("all",wmall);
        model.addAttribute("succes","Dodano nowego pracownika");
        return user.checkAdmin("pracownicy");
    }
    
    @GetMapping("/UsunPracownika") 
    public String usun(Model model,@RequestParam Long ID) {
    	user = new CheckAuth(model);
    	pracownicyRepository.deleteById(ID);
    	userRepository.deleteById(ID);
    	   List<Pracownik> all = pracownicyRepository.findAll();
           model.addAttribute("all", all);
           model.addAttribute("succes","Usunięto Pracownika");
    	return user.checkPracownik("pracownicy");
    }
    
    @GetMapping("/EdytujPracownika")
    public String edytuj(Model model,@RequestParam Long ID) {
    	
    	Optional<User> user=userRepository.findById(ID);
    	Optional<Pracownik> prac=pracownicyRepository.findById(ID);
    	WorkerUserMerger mfo = new WorkerUserMerger(user.get(),prac.get());
    	model.addAttribute("mfo",mfo);
    	return "edycjaPracownika";
    }
}
