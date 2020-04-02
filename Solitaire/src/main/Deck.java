package main;

import java.util.ArrayList;

/**
 * Defines a deck of 52 cards. Includes
 * necessary functions to view and shuffle cards in the
 * deck.
 * 
 * @author Emery Comstock
 */
public class Deck {
	/**
	 * The Array of Cards that will hold all 52
	 * unique card
	 */
	private Card[] cards;
	
	/**
	 * Constructor for the Deck, initializes {@code cards} and
	 * populates the array with 52 unique standard cards.
	 */
	public Deck() {		
		cards = new Card[52];

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 13; j++)
				cards[i * 13 + j] = new Card(i, j);
	}

	/**
	 * Used to get a Card object from a specific index
	 * in {@code cards}.
	 * 
	 * @param i The index at which the Card is to be taken from.
	 * @return The Card object at index {@code i}
	 * @return {@code null} if {@code i} is not within the range of the deck (0 to 51)
	 */
	public Card get(int i) {
		if (i < 52 && i >= 0)
			return cards[i];
		return null;
	}

	/**
	 * Used to get a copy of the Array {@code cards}.
	 * 
	 * @return A copy of {@code cards}, not a reference to it.
	 */
	public ArrayList<Card> getCopy(){
		ArrayList<Card> newList = new ArrayList<Card>();
		for (int i = 0; i < 52; i++)
			newList.add(cards[i]);
		return newList;
	}

	/**
	 * Used to shuffle the Deck. This method randomizes the
	 * location of every Card in the Deck.
	 */
	public void shuffle() {
		for (int i = 0; i < 52; i++) {
			int randomIndex = (int)(51 * Math.random());

			Card temp = cards[i];
			cards[i] = cards[randomIndex];
			cards[randomIndex] = temp;
		}
	}

}
