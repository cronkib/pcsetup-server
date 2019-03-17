package net.astercrono.pcsetup.dataaccess;

import java.util.Optional;

import net.astercrono.pcsetup.domain.profile.UserAuthentication;

public interface AuthenticationDao {
	Optional<UserAuthentication> getUserAuthentication(String username);
	
	UserAuthentication updateUserAuthentication(UserAuthentication authentication);
}
