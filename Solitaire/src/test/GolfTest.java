package test;

import static org.junit.Assert.*;
import org.junit.Test;

import main.Card;
import main.Golf;

/**
 * Tests the functionality of the Golf class in its current state.
 * 
 * @author Emery Comstock
 * @author Alan
 */
public class GolfTest {
	/**
	 * Tests that Golf can determine that no card can be added to the tableau piles
	 * from the stock pile.
	 */
	@Test
	public void testCantAddToTableauPile0FromStockPile() {
		Golf g = new Golf();
		g.addCardToPile("Tableau Pile 0", new Card(0, 1));
		assertFalse(g.isValidPlay("Stock Pile", "Tableau Pile 0", new Card(0, 0)));
	}
	
	/**
	 * Tests that Golf can determine that no card can be added to the tableau piles
	 * from the homecell pile.
	 */
	@Test
	public void testCantAddToTableauPile2FromHomecellPile() {
		Golf g = new Golf();
		g.addCardToPile("Tableau Pile 2", new Card(0, 1));
		assertFalse(g.isValidPlay("Homecell Pile", "Tableau Pile 2", new Card(0, 0)));
	}
	
	/**
	 * Tests that Golf can determine that no card can be added to the tableau piles
	 * from another tableau pile.
	 */
	@Test
	public void testCantAddToTableauPile3FromTableauPile1() {
		Golf g = new Golf();
		g.addCardToPile("Tableau Pile 3", new Card(0, 1));
		assertFalse(g.isValidPlay("Tableau Pile 1", "Tableau Pile 3", new Card(0, 0)));
	}
	
	/**
	 * Tests that all tableau piles start with 5 cards including the first one.
	 */
	@Test
	public void testTableauPile0InitiallyHolds5Cards() {
		Golf g = new Golf();
		assertEquals(5, g.getNumCardsInPile("Tableau Pile 0"));
	}
	
	/**
	 * Tests that all tableau piles start with 5 cards including the last one.
	 */
	@Test
	public void testTableauPile6InitiallyHolds5Cards() {
		Golf g = new Golf();
		assertEquals(5, g.getNumCardsInPile("Tableau Pile 6"));
	}
	
	/**
	 * Tests that tableau piles with cards in them can be drawn from.
	 */
	@Test
	public void testTableauPile0CanBeDrawnFromWhenNotEmpty() {
		Golf g = new Golf();
		assertTrue(g.isValidDraw("Tableau Pile 0"));
	}
	
	/**
	 * Tests that tableau piles that are empty cannot be drawn from.
	 */
	@Test
	public void testTableauPile6CannotBeDrawnFromWhenEmpty() {
		Golf g = new Golf();
		for (int i = 5; i > 0; i--)
			g.removeCardFromPile("Tableau Pile 6");
		
		assertFalse(g.isValidDraw("Tableau Pile 6"));
	}
	
	/**
	 * Tests that removing a card from a tableau pile changes its count
	 * from 5 to 4.
	 */
	@Test
	public void testRemovalFromTaleauPile0DecreasesNumberOfCards() {
		Golf g = new Golf();
		g.removeCardFromPile("Tableau Pile 0");
		assertEquals(4, g.getNumCardsInPile("Tableau Pile 0"));
	}
	
	/**
	 * Tests that removing a card from a tableau pile changes the top
	 * card to that which was previously behind the top card.
	 * (Top card is index 0 in this case)
	 */
	@Test
	public void testRemovalFromTableauPile6MakesNextCardTopCard() {
		Golf g = new Golf();
		Card nextInLine = g.getCardInPile("Tableau Pile 6", 1);
		g.removeCardFromPile("Tableau Pile 6");
		assertEquals(nextInLine, g.getCardInPile("Tableau Pile 6", 0));
	}
	
	/**
	 * Tests that attempting to remove a card from a null
	 * identifier returns a null card object.
	 */
	@Test
	public void testRemovalFromNullIdentifierReturnsNull() {
		Golf g = new Golf();
		assertEquals(null, g.getCardInPile(null, 0));
	}
	
	/**
	 * Tests that attempting to remove a card from an invalid
	 * identifier returns a null card object.
	 */
	@Test
	public void testRemovalFromInvalidIdentifierReturnsNull() {
		Golf g = new Golf();
		assertEquals(null, g.getCardInPile("Tablea u Pile 0", 0));
	}
	
	/**
	 * Tests that Golf can determine that no card can be added to the stock pile
	 * from the tableau piles.
	 */
	@Test
	public void testCantAddToStockpileFromTableauPile1() {
		Golf g = new Golf();
		g.addCardToPile("Stock Pile", new Card(1, 5));
		assertFalse(g.isValidPlay("Tableau Pile 1", "Stock Pile", new Card(1, 6)));
	}
	
	/**
	 * Tests that Golf can determine that no card can be added to the stock pile
	 * from the homecell pile.
	 */
	@Test
	public void testCantAddToStockpileFromHomecellPile() {
		Golf g = new Golf();
		g.addCardToPile("Stock Pile", new Card(1, 5));
		assertFalse(g.isValidPlay("Homecell Pile", "Stock Pile", new Card(1, 6)));
	}
	
	/**
	 * Tests that Golf can determine that no card can be added to the stock pile
	 * from itself.
	 */
	@Test
	public void testCantAddToStockpileFromItself() {
		Golf g = new Golf();
		g.addCardToPile("Stock Pile", new Card(1, 5));
		assertFalse(g.isValidPlay("Stock Pile", "Stock Pile", new Card(1, 6)));
	}
	
	/**
	 * Tests that stock pile initially holds 17 cards
	 */
	@Test
	public void testStockPileInitiallyHolds17Cards() {
		Golf g = new Golf();
		assertEquals(17, g.getNumCardsInPile("Stock Pile"));
	}
	
	/**
	 * Tests that Golf can tell that cards can be removed from the stock pile
	 * if it isn't empty.
	 */
	@Test
	public void testCanRemoveCardFromStockPileWhenNotEmpty() {
		Golf g = new Golf();
		assertTrue(g.isValidDraw("Stock Pile"));
	}
	
	/**
	 * Tests that Golf can tell that cards can't be removed from the stock pile once it
	 * is empty.
	 */
	@Test
	public void testCannotRemoveCardFromStockPileWhenEmpty() {
		Golf g = new Golf();
		for (int i = 17; i > 0; i--)
			g.removeCardFromPile("Stock Pile");
		assertFalse(g.isValidDraw("Stock Pile"));
	}
	
	/**
	 * Tests that removing a card from the stock pile makes
	 * the next card in line the new top card.
	 * (Top card is index 0 in this case)
	 */
	@Test
	public void testRemovingCardFromStockPileMakesNextCardTopCard() {
		Golf g = new Golf();
		Card nextInLine = g.getCardInPile("Stock Pile", 1);
		g.removeCardFromPile("Stock Pile");
		assertEquals(nextInLine, g.getCardInPile("Stock Pile", 0));
	}
	
	/**
	 * Tests that in the event of a draw from an empty pile
	 * the returned object is a null card object.
	 */
	@Test
	public void testRemovingCardFromEmptyStockPileReturnsNullCard() {
		Golf g = new Golf();
		for (int i = 17; i > 0; i--)
			g.removeCardFromPile("Stock Pile");
		
		assertEquals(null, g.removeCardFromPile("Stock Pile"));
	}
	
	/**
	 * Tests that removing a card from the stock pile decreases its
	 * number of cards by 1.
	 */
	@Test
	public void testRemovalFromStockPileDecreasesNumberOfCardsTo16() {
		Golf g = new Golf();
		g.removeCardFromPile("Stock Pile");
		assertEquals(16, g.getNumCardsInPile("Stock Pile"));
	}
	
	/**
	 * Tests that in the event that removing a card from an empty stock
	 * pile is attempted that the number of cards does not decrease below 0.
	 */
	@Test
	public void testRemoveFromEmptyStockPileKeepsNumberOfCardsAt0() {
		Golf g = new Golf();
		for (int i = 17; i > 0; i--)
			g.removeCardFromPile("Stock Pile");
		
		g.removeCardFromPile("Stock Pile");
		assertEquals(0, g.getNumCardsInPile("Stock Pile"));
	}
	
	/**
	 * Tests that Golf can determine that cards can be added to the homecell pile
	 * any card from the stock pile.
	 */
	@Test
	public void testCanAddToHomecellFromStockPileWithNonAdjacentCard() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 3));
		assertTrue(g.isValidPlay("Stock Pile", "Homecell Pile", new Card(0, 10)));
	}
	
	/**
	 * Tests that Golf can determine that cards cannot be added to the homecell pile from itself.
	 */
	@Test
	public void testCantAddToHomecellPileFromHomecellPile() {
		Golf g = new Golf();
		assertFalse(g.isValidPlay("Homecell Pile", "Homecell Pile", new Card(0, 10)));
	}
	
	/**
	 * Tests that Golf can determine that cards can be added to the homecell pile
	 * from a tableau pile with an adjacent card.
	 */
	@Test
	public void testCanAddToHomecellFromTableauWithTopCardJustAbove() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 0));
		assertTrue(g.isValidPlay("Tableau Pile 1", "Homecell Pile", new Card(0, 1)));
	}
	
	/**
	 * Tests that Golf can determine that adjacent cards can be added to the homecell pile
	 * from a tableau pile even with cards of different suits.
	 */
	@Test
	public void testCanAddToHomecellFromTableauWithTopCardJustBelowWithDifferentSuit() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 1));
		assertTrue(g.isValidPlay("Tableau Pile 2", "Homecell Pile", new Card(2, 0)));
	}
	
	/**
	 * Tests that Golf can determine that cards can be added to the homecell pile
	 * from a tableau pile when the card is adjacent through wrapping upwards.
	 */
	@Test
	public void testCanAddToHomecellFromTableauWithTopCardWrappedFromLow() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(3, 12));
		assertTrue(g.isValidPlay("Tableau Pile 4", "Homecell Pile", new Card(0, 0)));
	}
	
	/**
	 * Tests that Golf can determine that cards can be added to the homecell pile
	 * from a tableau pile when the card is adjacent through wrapping downwards.
	 */
	@Test
	public void testCanAddToHomecellFromTableauWithTopCardWrappedWrappedFromHigh() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(3, 0));
		assertTrue(g.isValidPlay("Tableau Pile 5", "Homecell Pile", new Card(0, 12)));
	}
	
	/**
	 * Tests that Golf can determine that cards cannot be added to the homecell pile when the
	 * added card is not adjacent.
	 */
	@Test
	public void testCantAddToHomecellFromTableauWithTopCardNotAdjacent() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 1));
		assertFalse(g.isValidPlay("Tableau Pile 6", "Homecell Pile", new Card(0, 6)));
	}
	
	/**
	 * Tests that Golf can determine that cards cannot be added to the homecell pile when the
	 * identifier of the origin pile is invalid.
	 */
	@Test
	public void testCantAddToHomecellPileFromInvalidId() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 1));
		assertFalse(g.isValidPlay("Tablea u Pile 1", "Homecell Pile", new Card(0, 0)));
	}
	
	/**
	 * Tests that Golf can determine that cards cannot be added to the homecell pile when the
	 * identifier of the origin pile is null.
	 */
	@Test
	public void testCantAddToHomecellPileFromNullId() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 1));
		assertFalse(g.isValidPlay(null, "Homecell Pile", new Card(0, 0)));
	}
	
	/**
	 * Tests that the homecell pile is initially given no cards.
	 */
	@Test
	public void testHomecellPileInitiallyHolds0Cards() {
		Golf g = new Golf();
		assertEquals(0, g.getNumCardsInPile("Homecell Pile"));
	}
	
	/**
	 * Tests that the homecell pile cannot be drawn from
	 * when the pile is empty.
	 */
	@Test
	public void testCannotRemoveFromHomecellPileWhenEmpty() {
		Golf g = new Golf();
		assertFalse(g.isValidDraw("Homecell Pile"));
	}
	
	/**
	 * Tests that the homecell pile cannot be drawn from
	 * even when it is not empty.
	 */
	@Test
	public void testCannotRemoveFromHomecellPileWhenNotEmpty() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 0));
		assertFalse(g.isValidDraw("Homecell Pile"));
	}
	
	/**
	 * Tests that adding cards to the homecell pile increases
	 * the number of cards in the pile.
	 */
	@Test
	public void testAddingCardToHomecellPileIncreasesNumberOfCards() {
		Golf g = new Golf();
		g.addCardToPile("Homecell Pile", new Card(0, 0));
		assertEquals(1, g.getNumCardsInPile("Homecell Pile"));
	}
	
	/**
	 * Tests that when a card is added to the homecell pile it
	 * becomes the top card.
	 * (The top card is index 0 in this case)
	 */
	@Test
	public void testAddingCardToHomecellPileMakesThatCardNewTopCard() {
		Golf g = new Golf();
		Card nextInLine = new Card(0, 0);
		g.addCardToPile("Homecell Pile", new Card(3,3));
		g.addCardToPile("Homecell Pile", nextInLine);
		assertEquals(nextInLine, g.getCardInPile("Homecell Pile", 0));
	}
}
