package com.lorian.frontend_service.security.authentication;

import com.lorian.frontend_service.security.authentication.DTOs.register.FormRegisterDTO;

public class VerifyRegister {

	protected static Boolean verify(FormRegisterDTO form) {
		return form.getPassword().equals(form.getConfirm_password()) 
				|| form.getUsername().isBlank() 
				|| form.getUsername().isEmpty()
				|| form.getEmail().isBlank()
				|| form.getEmail().isEmpty()
				|| form.getPassword().isBlank()
				|| form.getPassword().isEmpty()
				|| form.getConfirm_password().isBlank()
				|| form.getConfirm_password().isEmpty();
	}
	
}
