package pl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.model.User;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String register(Model model)
    {
		CheckAuth user = new CheckAuth(model);
		return user.checkPracownik("admin");
    }
}
