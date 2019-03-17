package net.astercrono.pcsetup.service.impl;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.astercrono.pcsetup.dataaccess.ProfileDao;
import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.domain.profile.UserAuthentication;
import net.astercrono.pcsetup.exception.profile.UsernameAlreadyExistsException;
import net.astercrono.pcsetup.service.ProfileService;
import net.astercrono.pcsetup.util.CryptUtil;
import net.astercrono.pcsetup.validation.ValidationException;
import net.astercrono.pcsetup.validation.ValidationMessages;

@Service
@Transactional
public class MainProfileService implements ProfileService {
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private CryptUtil cryptUtil;

	@Override
	public Profile getProfile(Long id) {
		return profileDao.getProfile(id);
	}

	@Override
	@Transactional
	public void createProfile(Profile profile, String password) throws ValidationException {
		try {
			if (profileDao.profileExists(profile.getUsername())) {
				throw new UsernameAlreadyExistsException();
			}
			
			UserAuthentication auth = createUserAuthentication(profile, password);
			profile.setAuthentication(auth);
			
			profileDao.createProfile(profile);
		} catch (UsernameAlreadyExistsException ex) {
			throw new ValidationException(new ValidationMessages("Username already exists"));
		} catch (Exception ex) {
			throw new ValidationException(new ValidationMessages("Internal error creating profile"));
		}
	}

	@Override
	@Transactional
	public Profile updateProfile(Profile profile) {
		return profileDao.updateProfile(profile);
	}

	@Override
	@Transactional
	public void deleteProfile(Long id) {
		profileDao.deleteProfile(id);
	}

	private UserAuthentication createUserAuthentication(Profile profile, String password)
			throws GeneralSecurityException {
		String ciphertext = cryptUtil.securePassword(password);
		
		UserAuthentication auth = new UserAuthentication();
		auth.setCiphertext(ciphertext);
		auth.setExpirationTimestamp(createDefaultExpirationTimestamp());
		auth.setProfile(profile);
		
		return auth;
	}
	
	private Date createDefaultExpirationTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, 2);
		return cal.getTime();
	}
}
