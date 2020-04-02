package exceptions;

/**
 * Exception class used to denote that a card cannot be played on top
 * of the card that the user wishes to place it.
 * 
 * @author Emery
 */
public class CardMismatchException extends SolitaireException {
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
		return "You cannot use this card here.";
	}
}
