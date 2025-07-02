package com.lorian.frontend_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuritySettings {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(req -> req
						.requestMatchers(HttpMethod.GET, "/login.html").permitAll()
						.requestMatchers(HttpMethod.POST, "/login.html").permitAll()
						.anyRequest().authenticated())
				.build();
	}
	
}
