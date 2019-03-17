package net.astercrono.pcsetup.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.astercrono.pcsetup.dataaccess.AuthenticationDao;
import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.domain.profile.UserAuthentication;
import net.astercrono.pcsetup.exception.authentication.InvalidPasswordException;
import net.astercrono.pcsetup.model.auth.AuthenticationTokens;
import net.astercrono.pcsetup.service.AuthenticationService;
import net.astercrono.pcsetup.util.CryptUtil;
import net.astercrono.pcsetup.validation.ValidationException;
import net.astercrono.pcsetup.validation.ValidationMessages;

@Service
public class MainAuthenticationService implements AuthenticationService {
	@Autowired
	private CryptUtil cryptUtil;
	@Autowired
	private AuthenticationDao authDao;

	@Override
	public Optional<AuthenticationTokens> login(String username, String password) throws ValidationException {
		try {
			Optional<UserAuthentication> auth = authDao.getUserAuthentication(username);

			if (auth.isEmpty()) {
				throw new ValidationException(new ValidationMessages("User does not exist."));
			}

			if (cryptUtil.checkPassword(password, auth.get().getCiphertext())) {
				String accessToken = cryptUtil.createAccessToken(auth.get().getProfile().getId(), username);
				String refreshToken = cryptUtil.createRefreshToken(auth.get().getProfile().getId(), username);
				
				return Optional.of(new AuthenticationTokens(accessToken, refreshToken));
			}
			else {
				return Optional.empty();
			}
		} catch (InvalidPasswordException ex) {
			throw new ValidationException(new ValidationMessages("Invalid password"));
		} 
	}
	
	@Override
	public Optional<AuthenticationTokens> refreshAccess(String refreshToken) throws ValidationException {
		Optional<Long> userId = cryptUtil.verifyToken(refreshToken);
		
		if (userId.isEmpty()) {
			return Optional.empty();
		}
		
		Optional<UserAuthentication> auth = authDao.getUserAuthentication(userId.get());
		
		if (auth.isEmpty()) {
			throw new ValidationException(new ValidationMessages("User does not exist."));
		}
		
		Profile profile = auth.get().getProfile();
		
		String newAccessToken = cryptUtil.createAccessToken(profile.getId(), profile.getUsername());
		String newRefreshToken = cryptUtil.createRefreshToken(profile.getId(), profile.getUsername());
		
		return Optional.of(new AuthenticationTokens(newAccessToken, newRefreshToken));
	}
}
