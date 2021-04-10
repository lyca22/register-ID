package exceptions;

public class WrongDayException extends Exception {

	private static final long serialVersionUID = 1;

	public WrongDayException() {
		super("You can't register if the day's an even number and your last last ID's digit isn't. Same with the opposite case.");
	}
	
}
