package net.astercrono.pcsetup.dataaccess;

import java.util.List;

import net.astercrono.pcsetup.domain.HardwareSetting;

public interface HardwareDao {
	List<HardwareSetting> getSettings();
	
	Long addHardwareSetting(HardwareSetting setting);
	
	HardwareSetting updateHardwareSetting(HardwareSetting setting);
	
	void removeHardwareSetting(Long id);
	
	HardwareSetting getSetting(Long id);
}
