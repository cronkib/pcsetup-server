package net.astercrono.pcsetup.validation;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String EXCEPTION_MESSAGE = "Error Validating";
	private ValidationMessages validationMessages;

	public ValidationException(ValidationMessages validationMessages) {
		super(EXCEPTION_MESSAGE);
		this.validationMessages = validationMessages;
	}

	public ValidationException(ValidationMessages validationMessages, Throwable t) {
		super(EXCEPTION_MESSAGE, t);
		this.validationMessages = validationMessages;
	}
	
	public ValidationMessages getValidationMessages() {
		return validationMessages;
	}
}
