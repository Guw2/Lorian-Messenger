package com.lorian.auth_service.user.DTOs;

import com.lorian.auth_service.user.User;

public record UserGetDTO(Long id, String username) {

	public UserGetDTO(User user) {
		this(user.getId(), user.getUsername());
	}
	
}
