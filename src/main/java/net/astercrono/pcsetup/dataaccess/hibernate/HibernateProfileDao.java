package net.astercrono.pcsetup.dataaccess.hibernate;

import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.Query;

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
		profile.setCreatedTimestamp(new Date());
		profile.setModifiedTimestamp(new Date());
		sessionFactory.getCurrentSession().persist(profile);
	}

	@Override
	public Profile updateProfile(Profile profile) {
		profile.setModifiedTimestamp(new Date());
		return (Profile) sessionFactory.getCurrentSession().merge(profile);
	}

	@Override
	public void deleteProfile(Long id) {
		String hql = "update Profile set deleted = true where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public boolean profileExists(String username) {
		String hql = "select 1 from Profile where username = :username";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);

		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
}
