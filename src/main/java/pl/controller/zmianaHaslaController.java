package pl.controller;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.model.User;
import pl.repository.UserRepository;

@Controller
public class zmianaHaslaController {
	
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
	        this.userRepository = userRepository;
	}
	
	@GetMapping("/zmianaHasla")
	public String home(Model model)
	{
		new CheckAuth(model);
		return "zmianaHasla";
	}
	
    @PostMapping("/changePassword")
    public String change(Model model, @RequestParam String oldPassword,@RequestParam String newPassword1,@RequestParam String newPassword2)
    {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
    	model.addAttribute("user",name);
        GrantedAuthority admins=new SimpleGrantedAuthority("ADMIN_ROLE");
        model.addAttribute("admin","admin");
        User user=userRepository.findFirstByEmail(name);




        if(!(oldPassword.equals(user.getHaslo())))
        {
            model.addAttribute("badOld","Złe hasło");
            return "zmianaHasla";
        }
        if(!newPassword1.equals(newPassword2))
        {
            model.addAttribute("NotTheSame","Hasła musza byc takie same");
            return "zmianaHasla";
        }
        user.setHaslo(newPassword1);
        userRepository.save(user);
        model.addAttribute("succes","Pomyslnie zmieniono hasło");
        return "zmianaHasla";
    }

}
