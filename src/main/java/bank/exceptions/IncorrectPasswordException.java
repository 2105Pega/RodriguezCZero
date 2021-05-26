package bank.exceptions;

public class IncorrectPasswordException extends Exception {

	public IncorrectPasswordException(String message) {
		super();
	}
	@Override
	public String getMessage() {
		final String invalidPasswordMessage = "Oops, wrong password!";
		return invalidPasswordMessage;
	}
}
