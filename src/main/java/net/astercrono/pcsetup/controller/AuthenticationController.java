package net.astercrono.pcsetup.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.model.PCSResponseModel;

@RestController
public class AuthenticationController {
	@PostMapping("/login")
	public PCSResponseModel<?> login() {
		return new PCSResponseModel<>("logged in");
	}

	@PostMapping("/logout")
	public PCSResponseModel<?> logout() {
		return new PCSResponseModel<>("logged out");
	}
}
