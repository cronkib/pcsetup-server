package net.astercrono.pcsetup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.dto.ProfileDto;
import net.astercrono.pcsetup.dto.mapper.ProfileMapper;
import net.astercrono.pcsetup.model.PCSResponseModel;
import net.astercrono.pcsetup.model.request.DeleteProfileRequest;
import net.astercrono.pcsetup.service.ProfileService;
import net.astercrono.pcsetup.validation.BindingResultValidator;
import net.astercrono.pcsetup.validation.ValidationException;

@RestController
public class ProfileController {
	@Autowired
	private ProfileMapper profileMapper;
	@Autowired
	private ProfileService profileService;

	@GetMapping("/profile/{id}")
	public PCSResponseModel<ProfileDto> getProfile(@PathVariable Long id) {
		Profile profile = profileService.getProfile(id);
		return new PCSResponseModel<>(profileMapper.mapDtoFromEntity(profile));
	}

	@PostMapping("/profile/create")
	public PCSResponseModel<Profile> createProfile(@RequestBody ProfileDto profileDto, BindingResult bindingResult)
			throws ValidationException {
		BindingResultValidator.validateBindingResult(bindingResult);
		Profile profile = profileMapper.mapEntityFromDto(profileDto);
		profileService.createProfile(profile);
		return new PCSResponseModel<>(profile);
	}

	@PostMapping("/profile/update")
	public PCSResponseModel<Profile> updateProfile(@RequestBody ProfileDto profileDto, BindingResult bindingResult)
			throws ValidationException {
		BindingResultValidator.validateBindingResult(bindingResult);
		Profile profile = profileMapper.mapEntityFromDto(profileDto);
		Profile updatedProfile = profileService.updateProfile(profile);
		return new PCSResponseModel<>(updatedProfile);
	}

	@PostMapping("/profile/delete")
	public PCSResponseModel<Profile> deleteProfile(@Valid @RequestBody DeleteProfileRequest profileRequest,
			BindingResult bindingResult) throws ValidationException {
		BindingResultValidator.validateBindingResult(bindingResult);
		profileService.deleteProfile(profileRequest.getId());
		return new PCSResponseModel<>();
	}
}
