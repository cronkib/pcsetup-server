package net.astercrono.pcsetup.exception.profile;

public class UsernameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 7545475423143620571L;
	private static final String MESSAGE = "Username already exists.";

	public UsernameAlreadyExistsException() {
		super(MESSAGE);
	}
	
	public UsernameAlreadyExistsException(Throwable t) {
		super(MESSAGE, t);
	}
}
