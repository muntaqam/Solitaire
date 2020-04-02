package exceptions;

/**
 * Abstract class used to guarantee that all exceptions thrown
 * in a game of solitaire will have a corresponding error message.
 * 
 * @author Emery
 */
public abstract class SolitaireException extends Exception {
	/**
	 * Version information.
	 */
	private static final long serialVersionUID = 0;
	
	/**
	 * Abstract method. Defines the proper error message when a solitaire exception is thrown.
	 */
	public abstract String getErrorMessage();
}
