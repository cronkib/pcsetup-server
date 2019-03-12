package net.astercrono.pcsetup.dataaccess.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.astercrono.pcsetup.dataaccess.ProfileDao;
import net.astercrono.pcsetup.domain.Profile;

@Repository
public class HibernateProfileDao implements ProfileDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Profile getProfile(Long id) {
		return sessionFactory.getCurrentSession().find(Profile.class, id);
	}

	@Override
	public void createProfile(Profile profile) {
		sessionFactory.getCurrentSession().persist(profile);
	}

	@Override
	public Profile updateProfile(Profile profile) {
		return (Profile) sessionFactory.getCurrentSession().merge(profile);
	}

	@Override
	public void deleteProfile(Profile profile) {
		sessionFactory.getCurrentSession().delete(profile);
	}
}
