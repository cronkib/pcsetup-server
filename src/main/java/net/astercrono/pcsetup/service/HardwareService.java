package net.astercrono.pcsetup.service;

import java.util.List;

import net.astercrono.pcsetup.domain.HardwareSetting;

public interface HardwareService {
	List<HardwareSetting> getSettings();

	HardwareSetting addHardwareSetting(HardwareSetting setting);

	HardwareSetting updateHardwareSetting(HardwareSetting setting);

	boolean removeHardwareSetting(Long id);
}
