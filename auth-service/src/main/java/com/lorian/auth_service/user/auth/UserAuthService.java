package com.lorian.auth_service.user.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lorian.auth_service.user.User;
import com.lorian.auth_service.user.UserRepository;
import com.lorian.auth_service.user.DTOs.UserGetDTO;
import com.lorian.auth_service.user.DTOs.UserInfoDTO;
import com.lorian.auth_service.user.DTOs.UserLoginDTO;
import com.lorian.auth_service.user.DTOs.UserRegisterDTO;

@Service
public class UserAuthService {

	private final UserRepository repo;
	private final PasswordEncoder encoder;
	private final AuthenticationManager authenticationManager;

	public UserAuthService(UserRepository repo, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
		this.repo = repo;
		this.encoder = encoder;
		this.authenticationManager = authenticationManager;
	}

	public UserInfoDTO login(UserLoginDTO login) {
		if(repo.findByUsername(login.username()).isPresent()) {
			try {
				Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.username(), login.password()));
				return new UserInfoDTO(login.username(), login.password());
			}catch(Exception e) {
				return null;
			}
		}
		
		throw new UsernameNotFoundException("User \"" + login.username() + "\" does not exist.");
	}
	
	public Boolean register(UserRegisterDTO register) {
		User user = new User();
		user.setUsername(register.username());
		user.setEmail(register.email());
		user.setPassword(encoder.encode(register.password()));
		
		repo.save(user);
		
		return true;
	}
	
}
