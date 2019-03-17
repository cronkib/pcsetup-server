package net.astercrono.pcsetup.dataaccess.hibernate;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.astercrono.pcsetup.dataaccess.AuthenticationDao;
import net.astercrono.pcsetup.domain.profile.UserAuthentication;

@Repository
public class HibernateAuthenticationDao implements AuthenticationDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Optional<UserAuthentication> getUserAuthentication(String username) {
		StringBuilder hql = new StringBuilder();
		hql.append("from UserAuthentication ");
		hql.append("where profile.username = :username ");

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("username", username);

		try {
			UserAuthentication auth = (UserAuthentication) query.getSingleResult();
			
			if (auth.isActive()) {
				return Optional.of(auth);
			}

			return Optional.empty();
		} catch (NoResultException ex) {
			return Optional.empty();
		}
	}

	@Override
	public UserAuthentication updateUserAuthentication(UserAuthentication authentication) {
		return (UserAuthentication) sessionFactory.getCurrentSession().merge(authentication);
	}

}
