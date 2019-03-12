package net.astercrono.pcsetup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.model.PCSResponseModel;
import net.astercrono.pcsetup.service.ProfileService;

@RestController
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	@GetMapping("/profile/{id}")
	public PCSResponseModel<Profile> getHardware(@PathVariable Long id) {
		Profile profile = profileService.getProfile(id);
		return new PCSResponseModel<>(profile);
	}

	@PostMapping("/profile/create")
	public PCSResponseModel<Profile> createProfile(@RequestBody Profile profile) {
		profileService.createProfile(profile);
		return new PCSResponseModel<>(profile);
	}

	@PostMapping("/profile/update")
	public PCSResponseModel<Profile> updateProfile(@RequestBody Profile profile) {
		Profile updatedProfile = profileService.updateProfile(profile);
		return new PCSResponseModel<>(updatedProfile);
	}
}
