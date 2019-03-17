package net.astercrono.pcsetup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.astercrono.pcsetup.model.PCSResponseModel;
import net.astercrono.pcsetup.model.PCSResponseStatus;
import net.astercrono.pcsetup.validation.ValidationException;
import net.astercrono.pcsetup.validation.ValidationMessages;

@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<PCSResponseModel<ValidationMessages>> handleValidationException(
			ValidationException exception) {
		return new ResponseEntity<>(
				new PCSResponseModel<>(exception.getValidationMessages(), PCSResponseStatus.VALIDATION_ERROR),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<PCSResponseModel<ValidationMessages>> handleException(ValidationException exception) {
		return new ResponseEntity<>(new PCSResponseModel<>(new ValidationMessages("An unexpected error occurred"),
				PCSResponseStatus.INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
