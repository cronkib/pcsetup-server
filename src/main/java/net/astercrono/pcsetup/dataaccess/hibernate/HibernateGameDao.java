package net.astercrono.pcsetup.dataaccess.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.astercrono.pcsetup.dataaccess.GameDao;
import net.astercrono.pcsetup.domain.Game;

@Repository
public class HibernateGameDao implements GameDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Game> getGames() {
		String hql = "from Game";
		return sessionFactory.getCurrentSession().createQuery(hql, Game.class).list();
	}
}
