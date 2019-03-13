package net.astercrono.pcsetup.validation;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class BindingResultValidator {
	public static void validateBindingResult(BindingResult result) throws ValidationException {
		if (result.getErrorCount() <= 0) {
			return;
		}
		
		ValidationMessages messages = new ValidationMessages();
		
		List<ObjectError> errors = result.getAllErrors();
		for (ObjectError e : errors) {
			if (e instanceof FieldError) {
				FieldError fe = (FieldError) e;
				messages.add(e.getObjectName() + "." + fe.getField() + ":" + e.getDefaultMessage());
			}
			else {
				messages.add(e.getObjectName() + ":" + e.getDefaultMessage());
			}
		}
		
		throw new ValidationException(messages);
	}
}
