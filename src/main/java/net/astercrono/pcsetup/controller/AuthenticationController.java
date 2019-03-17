package net.astercrono.pcsetup.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.model.PCSResponseModel;
import net.astercrono.pcsetup.model.auth.AuthenticationTokens;
import net.astercrono.pcsetup.model.request.LoginRequest;
import net.astercrono.pcsetup.service.AuthenticationService;
import net.astercrono.pcsetup.validation.ValidationException;
import net.astercrono.pcsetup.validation.ValidationMessages;

@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/login")
	public PCSResponseModel<?> login(@Valid @RequestBody LoginRequest loginRequest) throws ValidationException {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		
		Optional<AuthenticationTokens> token = authService.login(username, password);
		
		if (token.isEmpty()) {
			throw new ValidationException(new ValidationMessages("Login failed: Invalid username or password"));
		}
		
		return new PCSResponseModel<>(token);
	}
}
