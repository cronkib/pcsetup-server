package net.astercrono.pcsetup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.astercrono.pcsetup.dataaccess.ProfileDao;
import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.service.ProfileService;

@Service
@Transactional
public class MainProfileService implements ProfileService {
	@Autowired
	private ProfileDao profileDao;

	@Override
	public Profile getProfile(Long id) {
		return profileDao.getProfile(id);
	}

	@Override
	public void createProfile(Profile profile) {
		profileDao.createProfile(profile);
	}

	@Override
	public Profile updateProfile(Profile profile) {
		return profileDao.updateProfile(profile);
	}

	@Override
	public void deleteProfile(Profile profile) {
		profileDao.deleteProfile(profile);
	}
}
