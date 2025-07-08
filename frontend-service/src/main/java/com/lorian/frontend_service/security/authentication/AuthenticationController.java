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

import com.lorian.frontend_service.security.authentication.DTOs.AuthenticationDTO;
import com.lorian.frontend_service.security.authentication.DTOs.FormDTO;

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
		
		ResponseEntity<Boolean> response = restTemplate.postForEntity(
				"http://localhost:8081/auth/login",
				authenticationDto,
				Boolean.class
		);
		
		if(Boolean.TRUE.equals(response.getBody())) {
			UserDetails user = new User(form.getUsername(), "", List.of());
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
			
			return "redirect:/messenger";
		}
		
		return "redirect:/login";
				
	}
	
	@GetMapping("/user/username")
	public String getLoggedUserUsername() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || !authentication.isAuthenticated()) {
	        return null;
	    }
	    
	    Object principal = authentication.getPrincipal();
	    if (principal instanceof User) {
	        return ((User) principal).getUsername();
	    } else if (principal instanceof String) {
	        return (String) principal;
	    }
	    
	    return null;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
}
