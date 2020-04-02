package exceptions;

/**
 * Exception class used to denote that a card cannot be drawn
 * from the pile that the user has attempted to draw from.
 * 
 * @author Emery
 */
public class InvalidCardOriginException extends SolitaireException {
	/**
	 * Version information.
	 */
	private static final long serialVersionUID = 0;

	/**
	 * Defines the proper error message when this exception is thrown.
	 * 
	 * @return The proper error message for this exception.
	 */
	public String getErrorMessage() {
		return "You cannot draw from this pile.";
	}
}
