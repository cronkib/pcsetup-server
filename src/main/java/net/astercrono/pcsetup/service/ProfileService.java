package net.astercrono.pcsetup.service;

import net.astercrono.pcsetup.domain.Profile;

public interface ProfileService {
	Profile getProfile(Long id);
	
	void createProfile(Profile profile);
	
	Profile updateProfile(Profile profile);
	
	void deleteProfile(Profile profile);
}
