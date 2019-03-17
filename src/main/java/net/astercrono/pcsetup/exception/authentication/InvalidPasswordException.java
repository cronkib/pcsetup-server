package net.astercrono.pcsetup.exception.authentication;

public class InvalidPasswordException extends Exception {
	private static final long serialVersionUID = 5001228461390518687L;
	private static final String MESSAGE = "Invalid password";

	public InvalidPasswordException() {
		super(MESSAGE);
	}
	
	public InvalidPasswordException(Throwable t) {
		super(MESSAGE, t);
	}
}
