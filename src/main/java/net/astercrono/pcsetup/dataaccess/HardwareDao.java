package net.astercrono.pcsetup.dataaccess;

import java.util.List;
import java.util.Set;

import net.astercrono.pcsetup.domain.HardwareSetting;

public interface HardwareDao {
	List<HardwareSetting> getSettings();
	
	void addHardwareSetting(HardwareSetting setting);
	
	void updateHardwareSettings(Set<HardwareSetting> settings);
	
	void removeHardwareSettings(Set<HardwareSetting> settings);
}
