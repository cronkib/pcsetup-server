package net.astercrono.pcsetup.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

import net.astercrono.pcsetup.annotation.ValidEmail;

public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			setContextError(context);
			return false;
		}

		boolean isValid = EmailValidator.getInstance().isValid(value);
		if (isValid) {
			return true;
		}
		
		setContextError(context);
		return false;
	}

	private void setContextError(ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("Invalid email format.").addConstraintViolation();
	}
}
