package net.astercrono.pcsetup.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import net.astercrono.pcsetup.annotation.ValidPassword;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			setContextError(context);
			return false;
		}

		PasswordValidator validator = new PasswordValidator(rules());

		RuleResult result = validator.validate(new PasswordData(value));

		if (result.isValid()) {
			return true;
		}

		setContextError(context);
		return false;
	}

	private List<Rule> rules() {
		List<Rule> rules = new ArrayList<>();
		rules.add(new LengthRule(8, 64));
		rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
		rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
		rules.add(new WhitespaceRule());
		return rules;
	}

	private void setContextError(ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("Invalid password format.").addConstraintViolation();
	}
}
