package com.lorian.auth_service.user.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorian.auth_service.user.DTOs.UserGetDTO;
import com.lorian.auth_service.user.DTOs.UserInfoDTO;
import com.lorian.auth_service.user.DTOs.UserLoginDTO;
import com.lorian.auth_service.user.DTOs.UserRegisterDTO;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

	private final UserAuthService service;
	
	public UserAuthController(UserAuthService service) {
		this.service = service;
	}

	@PostMapping("/login")
	public ResponseEntity<UserInfoDTO> login(@RequestBody UserLoginDTO dto) {
		return new ResponseEntity<>(service.login(dto), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody UserRegisterDTO dto) {
		return new ResponseEntity<>(service.register(dto), HttpStatus.ACCEPTED);
	}
	
}
