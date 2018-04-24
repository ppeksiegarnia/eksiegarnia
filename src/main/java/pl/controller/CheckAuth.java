package pl.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class CheckAuth {
	public CheckAuth(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	String name = auth.getName();
            model.addAttribute("user",name);
	}
	
	public String checkAdmin(String strona) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> all=auth.getAuthorities();
        GrantedAuthority admins=new SimpleGrantedAuthority("ADMIN_ROLE");
		if(all.contains(admins)) return strona;
		else return "error";
	}
	
	public String checkPracownik(String strona) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> all=auth.getAuthorities();
        GrantedAuthority admins=new SimpleGrantedAuthority("ADMIN_ROLE");
        GrantedAuthority WORKER=new SimpleGrantedAuthority("WORKER_ROLE");
		if(all.contains(admins) || all.contains(WORKER)) return strona;
		else return "error";
	}
	
}
