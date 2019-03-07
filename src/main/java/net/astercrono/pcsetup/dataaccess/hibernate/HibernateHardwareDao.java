package net.astercrono.pcsetup.dataaccess.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.astercrono.pcsetup.dataaccess.HardwareDao;
import net.astercrono.pcsetup.domain.HardwareSetting;

@Repository
public class HibernateHardwareDao implements HardwareDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HardwareSetting> getSettings() {
		String hql = "from HardwareSetting ";
		return sessionFactory.getCurrentSession().createQuery(hql, HardwareSetting.class).list();
	}

	@Override
	public Long addHardwareSetting(HardwareSetting setting) {
		return (Long) sessionFactory.getCurrentSession().save(setting);
	}

	@Override
	public HardwareSetting updateHardwareSetting(HardwareSetting setting) {
		return (HardwareSetting) sessionFactory.getCurrentSession().merge(setting);
	}

	@Override
	public void removeHardwareSetting(Long id) {
		String hql = "delete from HardwareSetting where id = :id";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

	@Override
	public HardwareSetting getSetting(Long id) {
		return sessionFactory.getCurrentSession().find(HardwareSetting.class, id);
	}
}
