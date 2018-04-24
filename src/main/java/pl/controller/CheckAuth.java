package pl.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class CheckAuth {
	Model model;
	public CheckAuth(Model pmodel) {
		model = pmodel;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> all=auth.getAuthorities();
        GrantedAuthority admins=new SimpleGrantedAuthority("ADMIN_ROLE");
        GrantedAuthority pracownik=new SimpleGrantedAuthority("WORKER_ROLE");
        	String name = auth.getName();
            model.addAttribute("user",name);
            if(all.contains(admins))
            {
            	model.addAttribute("admin","admin");
            }
            if(all.contains(pracownik))
            	model.addAttribute("worker","worker");
	}
	
	public String checkAdmin(String strona) {
		if(model.containsAttribute("admin")) return strona;
		else return "error";
	}
	
	public String checkPracownik(String strona) {
		if(model.containsAttribute("admin")||model.containsAttribute("worker")) return strona;
		else return "error";
	}
	
}
