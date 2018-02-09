package game.exceptions;

public class NegativeErrorException extends Exception {

	/**
	 * Serial ID for the class.
	 */
	private static final long serialVersionUID = -6221732239251252347L;

	public NegativeErrorException() {
		super("Value cannot be set to less than 0");
	}
	
}
