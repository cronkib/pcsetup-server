package net.astercrono.pcsetup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.astercrono.pcsetup.dataaccess.HardwareDao;
import net.astercrono.pcsetup.domain.HardwareSetting;
import net.astercrono.pcsetup.service.HardwareService;

@Repository
@Transactional
public class DefaultHardwareService implements HardwareService {
	@Autowired
	private HardwareDao hardwareDao;
	
	@Override
	public List<HardwareSetting> getSettings() {
		return hardwareDao.getSettings();
	}

	@Override
	public HardwareSetting addHardwareSetting(HardwareSetting setting) {
		Long id = hardwareDao.addHardwareSetting(setting);
		return hardwareDao.getSetting(id);
	}

	@Override
	public HardwareSetting updateHardwareSetting(HardwareSetting setting) {
		return hardwareDao.updateHardwareSetting(setting);
	}

	@Override
	public boolean removeHardwareSetting(Long id) {
		if (hardwareDao.getSetting(id) == null) {
			return false;
		}
		hardwareDao.removeHardwareSetting(id);
		return true;
	}
}
