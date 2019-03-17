package net.astercrono.pcsetup.service;

import java.util.Optional;

import net.astercrono.pcsetup.model.auth.AuthenticationTokens;
import net.astercrono.pcsetup.validation.ValidationException;

public interface AuthenticationService {
	Optional<AuthenticationTokens> login(String username, String password) throws ValidationException;
}
