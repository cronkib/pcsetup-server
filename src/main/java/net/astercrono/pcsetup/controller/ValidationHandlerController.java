package net.astercrono.pcsetup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.astercrono.pcsetup.model.PCSResponseModel;
import net.astercrono.pcsetup.model.PCSResponseStatus;
import net.astercrono.pcsetup.validation.ValidationException;
import net.astercrono.pcsetup.validation.ValidationMessages;

@ControllerAdvice
public class ValidationHandlerController {
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PCSResponseModel<ValidationMessages> handleValidationException(ValidationException exception) {
		return new PCSResponseModel<ValidationMessages>(exception.getValidationMessages(),
				PCSResponseStatus.VALIDATION_ERROR);
	}
}
