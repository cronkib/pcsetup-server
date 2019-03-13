package net.astercrono.pcsetup.validation;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BindingResultValidator {
	public static void validateBindingResult(BindingResult result) throws ValidationException {
		if (result.getErrorCount() <= 0) {
			return;
		}
		
		ValidationMessages messages = new ValidationMessages();
		
		List<ObjectError> errors = result.getAllErrors();
		for (ObjectError e : errors) {
			messages.add(e.getObjectName() + ":" + e.getDefaultMessage());
		}
		
		throw new ValidationException(messages);
	}
}
