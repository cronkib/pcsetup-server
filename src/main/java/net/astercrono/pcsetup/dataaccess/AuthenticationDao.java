package net.astercrono.pcsetup.dataaccess;

import java.util.Optional;

import net.astercrono.pcsetup.domain.profile.UserAuthentication;

public interface AuthenticationDao {
	Optional<UserAuthentication> getUserAuthentication(String username);

	Optional<UserAuthentication> getUserAuthentication(Long userId);
	
	UserAuthentication updateUserAuthentication(UserAuthentication authentication);
}
