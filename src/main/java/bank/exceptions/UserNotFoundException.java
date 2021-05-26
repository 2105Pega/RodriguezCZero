package bank.exceptions;

public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super(message);

		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		final String userNotFoundMessage = "INTRUDER ALERT";
		return userNotFoundMessage;
	}
}
