package net.astercrono.pcsetup.service;

import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.validation.ValidationException;

public interface ProfileService {
	Profile getProfile(Long id);
	
	void createProfile(Profile profile, String password) throws ValidationException;
	
	Profile updateProfile(Profile profile);
	
	void deleteProfile(Long id);
}
