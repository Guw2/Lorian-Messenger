package com.lorian.frontend_service.security.authentication;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.lorian.frontend_service.security.authentication.DTOs.login.AuthenticationDTO;
import com.lorian.frontend_service.security.authentication.DTOs.login.FormDTO;
import com.lorian.frontend_service.security.authentication.DTOs.login.InfoDTO;
import com.lorian.frontend_service.security.authentication.DTOs.register.AuthenticationRegisterDTO;
import com.lorian.frontend_service.security.authentication.DTOs.register.FormRegisterDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {
	
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute FormDTO form, HttpServletRequest request) {

		if (form.getUsername() == null || form.getUsername().isBlank()) {
		    return "redirect:/login?error=empty_username";
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		AuthenticationDTO authenticationDto = new AuthenticationDTO(form.getUsername(), form.getPassword());
		
		ResponseEntity<InfoDTO> response = restTemplate.postForEntity(
				"http://localhost:8081/auth/login",
				authenticationDto,
				InfoDTO.class
		);
		
		if(!response.getBody().equals(null)) {
			UserDetails user = new User(form.getUsername(), "", List.of());
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
			
			return "redirect:/messenger";
		}
		
		return "redirect:/login";
				
	}
	
	@PostMapping("/register")
	public String handleRegister(@ModelAttribute FormRegisterDTO form) {
		
		if(VerifyRegister.verify(form)) {
			return "redirect:/register?error=error";
		}
		
		RestTemplate template = new RestTemplate();
		
		AuthenticationRegisterDTO authenticationRegisterDTO = new AuthenticationRegisterDTO(
				form.getUsername(), form.getEmail(), form.getPassword()
		);
		
		ResponseEntity<Boolean> response = template.postForEntity(
				"http://localhost:8081/auth/register",
				authenticationRegisterDTO,
				Boolean.class);
		
		if(Boolean.TRUE.equals(response.getBody())) {
			return "redirect:/login";
		}
		
		return "redirect:/register";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
}
