package main;

import java.util.*;

/**
 * Defines a game of golf solitaire. Includes functions to
 * calculate the validity of certain game moves and carry
 * out those moves.
 * 
 * @author Emery Comstock
 * @author Alan
 */
public class Golf {
	/**
	 * A HashMap of ArrayLists with corresponding String identifiers. Each ArrayList
	 * represents a pile and the identifier denotes which pile it is.
	 */
	private Map<String, ArrayList<Card>> pileLookupPairs;
	
	/**
	 * The deck which contains the Card objects to be used in this instance of Golf.
	 */
	private Deck cards;

	/**
	 * Initializes the {@code pileLookUpPairs} HashMap with each pile and
	 * its corresponding String identifier.
	 */
	private void initHashMap() {
		pileLookupPairs = new HashMap<String, ArrayList<Card>>();
		pileLookupPairs.put("Stock Pile", new ArrayList<Card>());
		pileLookupPairs.put("Homecell Pile", new ArrayList<Card>());
		pileLookupPairs.put("Tableau Pile 0", new ArrayList<Card>());
		pileLookupPairs.put("Tableau Pile 1", new ArrayList<Card>());
		pileLookupPairs.put("Tableau Pile 2", new ArrayList<Card>());
		pileLookupPairs.put("Tableau Pile 3", new ArrayList<Card>());
		pileLookupPairs.put("Tableau Pile 4", new ArrayList<Card>());
		pileLookupPairs.put("Tableau Pile 5", new ArrayList<Card>());
		pileLookupPairs.put("Tableau Pile 6", new ArrayList<Card>());
	}
	
	
	/**
	 * Constructor for Golf. Triggers initialization method for {@code pileLookupPairs} and
	 * adds necessary Card objects to each pile from a new Deck object.
	 */
	public Golf() {
		initHashMap();

		cards = new Deck();
		cards.shuffle();
		ArrayList<Card> dealables = cards.getCopy();

		for (int i = 0; i <= 6; i++)
			for (int j = 0; j < 5; j++)
				pileLookupPairs.get("Tableau Pile " + Integer.toString(i)).add(dealables.remove(dealables.size() - 1));

		pileLookupPairs.replace("Stock Pile", dealables);
	}
	
	/**
	 * Used to get the number of cards in a given pile, so long as
	 * the {@code id} is a valid identifier.
	 * 
	 * @param id The identifier of the pile in question.
	 * @return The number of Card objects in the pile
	 * @return {@code -1} if the identifier is invalid 
	 */
	public int getNumCardsInPile(String id) {
		if (isValidId(id))
			return pileLookupPairs.get(id).size();
		return -1;
	}
	
	/**
	 * Used to get the Card object at a specific position in a specific pile,
	 * so long as {@code id} is a valid identifier and the index {@code i} is
	 * within the pile's range.
	 * 
	 * @param id The identifier of the pile in question.
	 * @param i The index at which the card is to be found.
	 * @return The Card object at {@code i}
	 * @return {@code null} if the identifier is invalid or the index is out of range
	 */
	public Card getCardInPile(String id, int i) {
		if (isValidId(id) && i < pileLookupPairs.get(id).size())
			return pileLookupPairs.get(id).get(i);
		return null;
	}
	
	/**
	 * Used to remove the top Card (index 0) from a given pile and
	 * return a reference to that Card object. Method also checks if the pile
	 * can be drawn from.
	 * 
	 * @param id The pile to be drawn from.
	 * @return The Card object that was removed from the pile
	 * @return {@code null} if the pile cannot be drawn from
	 */
	public Card removeCardFromPile(String id) {
		if (isValidDraw(id))
			return pileLookupPairs.get(id).remove(0);
		return null;
	}
	
	/**
	 * Used to add a Card object {@code c} to a given pile. Does not include a check for
	 * validity based on game rules, but does check the validity of the identifier {@code id}
	 * and that {@code c} is not {@code null}. For this reason, in a game scenario, this method
	 * should only be called from within the instance, after a complete validity check.
	 * 
	 * @param id Identifier of the pile that will be added to
	 * @param c Card object that is to be added to the specified pile
	 */
	public void addCardToPile(String id, Card c) {
		if (isValidId(id) && c != null)
			pileLookupPairs.get(id).add(0, c);
	}

	/**
	 * Used to validate if the Card object {@code c} on the top (index 0) of a given pile
	 * can be drawn. Checks that {@code id} is a valid identifier, that the pile in question 
	 * is not the homecell pile, which cannot be drawn from, and that the pile is not empty.
	 * 
	 * @param id Identifier of pile to be checked for draw validity
	 * @return {@code true} if the Card can be drawn
	 * @return {@code false} if the Card cannot be drawn
	 */
	public boolean isValidDraw(String id) {
		return (isValidId(id) && !id.equals("Homecell Pile") && pileLookupPairs.get(id).size() > 0);
	}

	/**
	 * Used to validate if the Card object {@code c} can be added to a given pile based on which pile {@code c}
	 * came from. Checks that {@code originId} and {@code destinId} are both valid identifiers. Checks that
	 * {@code c} is not {@code null}. In accordance with the rules of golf, this method checks that {@code originId} 
	 * is not the identifier for the homecell, and that {@code destinId} is. Once these conditions are met
	 * the method checks if either {@code originId} is the identifier for the stock pile, which would make any 
	 * card playable, or the {@code c} is of a value adjacent to that of the top card (index 0) of the homecell pile.
	 * 
	 * @param originId Identifier of the pile from which the Card object originated
	 * @param destinId Identifier of the pile to which the user is attempting to add the Card
	 * @param c Card object whose transfer is being validated
	 * @return {@code true} if the Card can be added
	 * @return {@code false} if the Card cannot be added
	 */
	public boolean isValidPlay(String originId, String destinId, Card c) {
		if (!(isValidId(originId) && isValidId(destinId) && !originId.equals("Homecell Pile") 
				&& destinId.equals("Homecell Pile")))
			return false;

		ArrayList<Card> destinPile = pileLookupPairs.get(destinId);

		return originId.equals("Stock Pile") || destinPile.size() == 0 || isAdjacentValue(c.getValue(),
				destinPile.get(0).getValue());
	}

	/**
	 * Used to check if the given identifier, {@code id}, is non {@code null} and 
	 * is contained in {@code pileLookupPairs}.
	 * 
	 * @param id Identifier in question
	 * @return {@code true} if the identifier is valid
	 * @return {@code false} if the identifier is invalid
	 */
	public boolean isValidId(String id) {
		return (id != null && pileLookupPairs.containsKey(id));
	}

	/**
	 * Used to check if two values, {@code v1} and {@code v2}, are adjacent in terms of 
	 * the rules of Golf. This includes the "wrapping" which makes 12 and 0 adjacent values.
	 * 
	 * @param v1 First value to be compared
	 * @param v2 Second value to be compared
	 * @return {@code true} if the values are adjacent
	 * @return {@code false} if the values aren't adjacent
	 */
	public boolean isAdjacentValue(int v1, int v2) {
		int upper = v1 + 1;
		int lower = v1 - 1;

		if (lower < 0)
			lower = 12;

		if (upper > 12)
			upper = 0;

		return v2 == lower || v2 == upper;
	}
}
