package com.lorian.auth_service.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long>{

	Optional<UserDetails> findByUsername(String username);
	
}
