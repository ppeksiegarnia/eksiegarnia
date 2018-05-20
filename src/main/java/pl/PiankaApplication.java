package pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

@SpringBootApplication
public class PiankaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiankaApplication.class, args);
	}
}
