package net.astercrono.pcsetup.dataaccess;

import net.astercrono.pcsetup.domain.Profile;

public interface ProfileDao {
	Profile getProfile(Long id);
	
	void createProfile(Profile profile);
	
	Profile updateProfile(Profile profile);
	
	void deleteProfile(Long id);
	
	boolean profileExists(String username);
}
