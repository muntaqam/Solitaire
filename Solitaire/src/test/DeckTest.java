package test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import main.Deck;

/**
 * Collection of test cases for the deck class, focusing on
 * unique card combinations before and after shuffles.
 * 
 * @author Emery Comstock
 */
public class DeckTest {	
	
	/**
	 * Tests that once a deck is created, all cards are unique.
	 */
	@Test
	public void constructorTestAllCardsUnique() {
		boolean result = true;
		Deck d = new Deck();
		
		for (int i = 0; i < 51; i++) 
			for (int j = i + 1; j < 52; j++) 	
				if (d.get(i).equals(d.get(j)))
					result = false;

		assertTrue(result);
	}
	
	/**
	 * Tests that after a shuffle, all cards in the deck
	 * are still unique.
	 */
	@Test
	public void shuffleTestAllCardsUnique() {
		boolean result = true;
		Deck d = new Deck();
		d.shuffle();
		d.shuffle();
		
		for (int i = 0; i < 51; i++) 
			for (int j = i + 1; j < 52; j++)
				if (d.get(i).equals(d.get(j)))
					result = false;

		assertTrue(result);
	}
}
