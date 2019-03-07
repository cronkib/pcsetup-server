package net.astercrono.pcsetup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.dataaccess.HardwareDao;
import net.astercrono.pcsetup.domain.HardwareSetting;

@RestController
public class HardwareController extends PCSetupController {
	@Autowired
	private HardwareDao hardwareDao;
	
	@GetMapping("/hardware")
	public List<HardwareSetting> getHardware() {
		return hardwareDao.getSettings();
	}
}
