package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.model.User;
import pl.repository.UserRepository;
import pl.service.UserService;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/register")
    public String register(Model model)
    {
    	new CheckAuth(model);
        model.addAttribute("users",new User());
        return "register";
    }

    @PostMapping("/register")
    public String zarejestruj(Model model, @ModelAttribute User user)
    {


        userService.addWithDefaultRole(user);
        return "redirect:success";
    }


    @GetMapping("/success")
    public String add(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("user",name);
        return  "index";
    }

    @GetMapping("/loginPage")
    public String login(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("user",name);
        model.addAttribute("users",new User());
        return "register";
    }
}
