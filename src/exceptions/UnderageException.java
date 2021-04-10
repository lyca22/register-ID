package exceptions;

public class UnderageException extends Exception {

	private static final long serialVersionUID = 1;

	public UnderageException() {
		super("You can't register if you are a minor.");
	}
	
}
