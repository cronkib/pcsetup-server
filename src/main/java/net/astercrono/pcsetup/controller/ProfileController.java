package net.astercrono.pcsetup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.model.PCSResponseModel;
import net.astercrono.pcsetup.model.PCSResponseStatus;
import net.astercrono.pcsetup.model.request.DeleteProfileRequest;
import net.astercrono.pcsetup.service.ProfileService;
import net.astercrono.pcsetup.validation.BindingResultValidator;
import net.astercrono.pcsetup.validation.ValidationException;
import net.astercrono.pcsetup.validation.ValidationMessages;

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

	@PostMapping("/profile/delete")
	public PCSResponseModel<Profile> deleteProfile(@Valid @RequestBody DeleteProfileRequest profileRequest,
			BindingResult bindingResult) throws ValidationException {
		BindingResultValidator.validateBindingResult(bindingResult);
		profileService.deleteProfile(profileRequest.getId());
		return new PCSResponseModel<>();
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PCSResponseModel<ValidationMessages> handleValidationException(ValidationException exception) {
		return new PCSResponseModel<ValidationMessages>(exception.getValidationMessages(), PCSResponseStatus.VALIDATION_ERROR);
	}
}
