package net.astercrono.pcsetup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.domain.HardwareSetting;
import net.astercrono.pcsetup.model.PCSResponseModel;
import net.astercrono.pcsetup.service.HardwareService;

@RestController
public class HardwareController extends PCSetupController {
	@Autowired
	private HardwareService hardwareService;

	@GetMapping("/hardware")
	public PCSResponseModel<List<HardwareSetting>> getHardware() {
		return new PCSResponseModel<List<HardwareSetting>>(hardwareService.getSettings());
	}

	@PostMapping("/hardware/addSetting")
	public PCSResponseModel<HardwareSetting> addSetting(@RequestBody HardwareSetting setting) {
		return new PCSResponseModel<HardwareSetting>(hardwareService.addHardwareSetting(setting));
	}

	@PostMapping("/hardware/updateSetting")
	public PCSResponseModel<HardwareSetting> updateSetting(@RequestBody HardwareSetting setting) {
		return new PCSResponseModel<HardwareSetting>(hardwareService.updateHardwareSetting(setting));
	}
	
	@PostMapping("/hardware/remove/{id}")
	public PCSResponseModel<Boolean> removeSetting(@PathVariable Long id) {
		return new PCSResponseModel<Boolean>(hardwareService.removeHardwareSetting(id));
	}
}
