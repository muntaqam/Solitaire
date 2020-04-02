package exceptions;

/**
 * Exception class used to denote that cards cannot be played to the
 * pile which the user has attempted to play to.
 * 
 * @author Emery
 */
public class InvalidCardDestinationException extends SolitaireException {
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
		return "You cannot play to this pile.";
	}
}
