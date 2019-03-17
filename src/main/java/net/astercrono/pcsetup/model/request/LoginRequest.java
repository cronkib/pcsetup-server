package net.astercrono.pcsetup.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.astercrono.pcsetup.annotation.ValidPassword;

public class LoginRequest {
	@NotNull
	@Size(min = 8, max = 64)
	private String username;
	@ValidPassword
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
