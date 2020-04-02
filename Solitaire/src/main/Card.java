package main;

/**
 * Defines a typical playing card with a suit, rank, and value.
 * 
 * @author Emery Comstock
 */
public class Card {
	/**
	 * Array of all possible suits that a Card object can have.
	 */
	private static String[] suits;
	
	/**
	 * Array of all possible ranks that a Card object can have.
	 */
	private static String[] ranks;

	/**
	 * A static initializer used to set the values of {@code suits} and {@code ranks}
	 */
	static {
		suits = new String[] {"Hearts", "Diamonds", "Spades", "Clubs"};
		ranks = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	}

	/**
	 * The suit that this specific Card has.
	 */
	private String suit;
	
	/**
	 * The rank that this specific Card has.
	 */
	private String rank;
	
	/**
	 * The value that this specific Card has.
	 */
	private int value;

	/**
	 * Constructor for the Card. Only used to set
	 * the suit, rank, and value of the Card.
	 * 
	 * @param s Will be used to set {@code suit} from the Array {@code suits}
	 * @param v Will be used to set {@code rank} from the Array {@code ranks} as well as {@code value} itself
	 */
	public Card(int s, int v) {
		suit = suits[s];
		rank = ranks[v];
		value = v;
	}

	/**
	 * Gets the current Card instance's suit.
	 * 
	 * @return The suit of the current Card instance
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * Gets the current Card instance's rank.
	 * 
	 * @return The rank of the current Card instance
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * Gets the current Card instance's value.
	 * 
	 * @return The value of the current Card instance
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Overrides default toString method to return the proper spoken or
	 * written representation of the current Card instance.
	 * (ex. "4 of Hearts" or "Jack of Clubs")
	 * 
	 * @return The String representation of the current Card instance
	 */
	public String toString() {
		return rank + " of " + suit;
	}

	/**
	 * Overrides default equals method to properly judge
	 * equality between two instances of Card.
	 * 
	 * @param c The Card to be compared to the current instance.
	 * @return {@code true} if the suit and value of both Card instances are the same
	 * @return {@code false} if the suit or value of both Card instances are different
	 */
	public boolean equals(Card c) {
		return suit.equals(c.getSuit()) && value == c.getValue();
	}
}
