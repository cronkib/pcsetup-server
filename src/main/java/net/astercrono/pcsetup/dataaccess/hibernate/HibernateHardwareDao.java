package net.astercrono.pcsetup.dataaccess.hibernate;

import java.util.List;
import java.util.Set;

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
	public void addHardwareSetting(HardwareSetting setting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateHardwareSettings(Set<HardwareSetting> settings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeHardwareSettings(Set<HardwareSetting> settings) {
		// TODO Auto-generated method stub
		
	}

}
