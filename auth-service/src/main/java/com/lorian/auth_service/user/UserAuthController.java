package com.lorian.auth_service.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.auth_service.user.DTOs.UserGetDTO;
import com.lorian.auth_service.user.DTOs.UserLoginDTO;
import com.lorian.auth_service.user.DTOs.UserRegisterDTO;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	private final UserService service;
	
	public UserAuthController(UserService service) {
		this.service = service;
	}

	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody UserLoginDTO dto) {
		return new ResponseEntity<>(service.login(dto), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody UserRegisterDTO dto) {
		return new ResponseEntity<>(service.register(dto), HttpStatus.ACCEPTED);
	}
	
}
