package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.Card;
import main.FortyThieves;

public class FortyThievesTest {
	@Test
	public void testTableauZeroHasThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 0"));
	}
	
	@Test
	public void testTableauOneHasThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 1"));
	}
	
	@Test
	public void testTableauTwoHasThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 2"));
	}
	
	@Test
	public void testTableauThreeStartsThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 3"));
	}
	
	@Test
	public void testTableauFiveStartsThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 5"));
	}
	
	@Test
	public void testTableauSevenStartsThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 7"));
	}
	
	@Test
	public void testTableauElevenStartsThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 11"));
	}
	
	@Test
	public void testTableauTwelveStartsThreeCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(3, ft.getNumCardsInPile("Tableau Pile 12"));
	}
	
	@Test
	public void testTableauAddIsLegalIdenticalSuitOneRankBelow() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 1);
		Card b = new Card(0, 0);
		
		ft.addCardToPile("Tableau Pile 0", a);
		assertTrue(ft.isValidPlay("Tableau Pile 1", "Tableau Pile 0", b));
	}
	
	@Test
	public void testTableauAddIsNotLegalIdenticalSuitSameRank() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 0);
		Card b = new Card(0, 0);
		
		ft.addCardToPile("Tableau Pile 0", a);
		assertFalse(ft.isValidPlay("Tableau Pile 1", "Tableau Pile 0", b));
	}
	
	@Test
	public void testTableauAddIsNotLegalIdenticalSuitOneRankAbove() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 0);
		Card b = new Card(0, 1);
		
		ft.addCardToPile("Tableau Pile 0", a);
		assertFalse(ft.isValidPlay("Tableau Pile 1", "Tableau Pile 0", b));
	}
	
	@Test
	public void testTableauAddIsNotLegalDifferentSuitOneRankBelow() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 1);
		Card b = new Card(2, 0);
		
		ft.addCardToPile("Tableau Pile 0", a);
		assertFalse(ft.isValidPlay("Tableau Pile 1", "Tableau Pile 0", b));
	}
	
	@Test
	public void testTableauAddIsLegalWhenEmpty() {
		FortyThieves ft = new FortyThieves();
		while(ft.getNumCardsInPile("Tableau Pile 0") != 0)
			ft.removeCardFromPile("Tableau Pile 0");

		assertTrue(ft.isValidPlay("Tableau Pile 1", "Tableau Pile 0", new Card(3, 9)));
	}
	
	@Test
	public void testTableauRemoveIsLegalWhenNotEmpty() {
		FortyThieves ft = new FortyThieves();
		ft.addCardToPile("Tableau Pile 0", new Card(0, 0));
		assertTrue(ft.isValidDraw("Tableau Pile 0"));
	}
	
	@Test
	public void testTableauRemoveIsNotLegalWhenEmpty() {
		FortyThieves ft = new FortyThieves();
		while(ft.getNumCardsInPile("Tableau Pile 0") != 0)
			ft.removeCardFromPile("Tableau Pile 0");
		
		assertFalse(ft.isValidDraw("Tableau Pile 0"));
	}
	
	@Test
	public void testTableauSizeIncreasesWhenCardAdded() {
		FortyThieves ft = new FortyThieves();
		ft.addCardToPile("Tableau Pile 0", new Card(0, 0));
		
		assertEquals(4, ft.getNumCardsInPile("Tableau Pile 0"));
	}
	
	@Test
	public void testTableauSizeIncreasesWhenCardAddedToEmpty() {
		FortyThieves ft = new FortyThieves();
		while(ft.getNumCardsInPile("Tableau Pile 0") != 0)
			ft.removeCardFromPile("Tableau Pile 0");
		
		ft.addCardToPile("Tableau Pile 0", new Card(0, 0));
		assertEquals(1, ft.getNumCardsInPile("Tableau Pile 0"));
	}
	
	@Test
	public void testTableauAddedCardIsOnTop() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 0);
		Card b = new Card(2, 10);
		
		ft.addCardToPile("Tableau Pile 0", a);
		ft.addCardToPile("Tableau Pile 0", b);
		assertEquals(b, ft.getCardInPile("Tableau Pile 0", 0));
	}
	
	@Test
	public void testTableauSizeDecreasesWhenCardRemoved() {
		FortyThieves ft = new FortyThieves();
		ft.removeCardFromPile("Tableau Pile 0");
		assertEquals(2, ft.getNumCardsInPile("Tableau Pile 0"));
	}
	
	@Test
	public void testTableauNextCardIsTopWhenCardRemoved() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 0);
		Card b = new Card(2, 10);
		
		ft.addCardToPile("Tableau Pile 0", a);
		ft.addCardToPile("Tableau Pile 0", b);
		ft.removeCardFromPile("Tableau Pile 0");
		assertEquals(a, ft.getCardInPile("Tableau Pile 0", 0));
	}
	
	@Test
	public void testHomecellPileZeroStartsOneCard() {
		FortyThieves ft = new FortyThieves();
		assertEquals(1, ft.getNumCardsInPile("Homecell Pile 0"));
	}
	
	@Test
	public void testHomecellPileOneStartsOneCard() {
		FortyThieves ft = new FortyThieves();
		assertEquals(1, ft.getNumCardsInPile("Homecell Pile 1"));
	}
	
	@Test
	public void testHomecellPileTwoStartsOneCard() {
		FortyThieves ft = new FortyThieves();
		assertEquals(1, ft.getNumCardsInPile("Homecell Pile 2"));
	}
	
	@Test
	public void testHomecellPileThreeStartsOneCard() {
		FortyThieves ft = new FortyThieves();
		assertEquals(1, ft.getNumCardsInPile("Homecell Pile 3"));
	}
	
	@Test
	public void testHomecellPileFiveStartsOneCard() {
		FortyThieves ft = new FortyThieves();
		assertEquals(1, ft.getNumCardsInPile("Homecell Pile 5"));
	}
	
	@Test
	public void testHomecellPileSevenStartsOneCard() {
		FortyThieves ft = new FortyThieves();
		assertEquals(1, ft.getNumCardsInPile("Homecell Pile 7"));
	}
	
	@Test
	public void testHomecellCanAddSameSuitOneRankAbove() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 0);
		Card b = new Card(0, 1);
		
		ft.addCardToPile("Homecell Pile 0", a);
		assertTrue(ft.isValidPlay("Tableau Pile 0", "Homecell Pile 0", b));
	}
	
	@Test
	public void testHomecellCantAddSameSuitSameRank() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 0);
		Card b = new Card(0, 0);
		
		ft.addCardToPile("Homecell Pile 0", a);
		assertFalse(ft.isValidPlay("Tableau Pile 0", "Homecell Pile 0", b));
	}
	
	@Test
	public void testHomecellCantAddSameSuitOneRankBelow() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 1);
		Card b = new Card(0, 0);
		
		ft.addCardToPile("Homecell Pile 0", a);
		assertFalse(ft.isValidPlay("Tableau Pile 0", "Homecell Pile 0", b));
	}
	
	@Test
	public void testHomecellCantAddDifferentSuitOneRankAbove() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(1, 0);
		Card b = new Card(0, 1);
		
		ft.addCardToPile("Homecell Pile 0", a);
		assertFalse(ft.isValidPlay("Tableau Pile 0", "Homecell Pile 0", b));
	}
	
	@Test
	public void testHomecellCanRemoveCardWhenNotEmpty() {
		FortyThieves ft = new FortyThieves();
		assertFalse(ft.isValidDraw("Homecell Pile 0"));
	}
	
	@Test
	public void testHomecellCantRemoveCardWhenEmpty() {
		FortyThieves ft = new FortyThieves();
		ft.removeCardFromPile("Homecell Pile 0");
		assertFalse(ft.isValidDraw("Homecell Pile 0"));
	}
	
	@Test
	public void testHomecellSizeIncreasesWhenCardAdded() {
		FortyThieves ft = new FortyThieves();
		ft.addCardToPile("Homecell Pile 0", new Card(0, 0));
		assertEquals(2, ft.getNumCardsInPile("Homecell Pile 0"));
	}
	
	@Test
	public void testHomecellTopCardIsNewlyAddedCard() {
		FortyThieves ft = new FortyThieves();
		Card c = new Card(1, 1);
		
		ft.addCardToPile("Homecell Pile 0", c);
		assertEquals(c, ft.getCardInPile("Homecell Pile 0", 0));
	}
	
	@Test
	public void testStockPileStartsFiftySevenCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(57, ft.getNumCardsInPile("Stock Pile"));
	}
	
	@Test
	public void testStockPileCantAddCards() {
		FortyThieves ft = new FortyThieves();
		assertFalse(ft.isValidPlay("Tableau Pile 0", "Stock Pile", new Card(0, 0)));
	}
	
	@Test
	public void testStockPileCanRemoveWhenNotEmpty() {
		FortyThieves ft = new FortyThieves();
		assertTrue(ft.isValidDraw("Stock Pile"));
	}
	
	@Test
	public void testStockPileCantRemoveWhenEmpty() {
		FortyThieves ft = new FortyThieves();
		while(ft.getNumCardsInPile("Stock Pile") != 0)
			ft.removeCardFromPile("Stock Pile");
		
		assertFalse(ft.isValidDraw("Stock Pile"));
	}
	
	@Test
	public void testStockPileRemovalMakesNextCardTopCard() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(1, 1);
		Card b = new Card(2, 2);
		Card c = new Card(3, 3);
		
		ft.addCardToPile("Stock Pile", a);
		ft.addCardToPile("Stock Pile", b);
		ft.addCardToPile("Stock Pile", c);
		ft.removeCardFromPile("Stock Pile");
		ft.removeCardFromPile("Stock Pile");
		assertEquals(a, ft.getCardInPile("Stock Pile", 0));
	}
	
	@Test
	public void testStockPileSizeDecreasesWhenCardRemoved() {
		FortyThieves ft = new FortyThieves();
		ft.removeCardFromPile("Stock Pile");
		assertEquals(56, ft.getNumCardsInPile("Stock Pile"));
	}
	
	@Test
	public void testWastePileStartsZeroCards() {
		FortyThieves ft = new FortyThieves();
		assertEquals(0, ft.getNumCardsInPile("Waste Pile"));
	}
	
	@Test
	public void testWastePileAddIsLegalFromStockPile() {
		FortyThieves ft = new FortyThieves();
		assertTrue(ft.isValidPlay("Stock Pile", "Waste Pile", new Card(0, 0)));
	}
	
	@Test
	public void testWastePileAddIsNotLegalFromTableauPile() {
		FortyThieves ft = new FortyThieves();
		assertFalse(ft.isValidPlay("Tableau Pile 0", "Waste Pile", new Card(0, 0)));
	}
	
	@Test
	public void testWastePileAddIsNotLegalFromHomecellPile() {
		FortyThieves ft = new FortyThieves();
		assertFalse(ft.isValidPlay("Homecell Pile 0", "Waste Pile", new Card(0, 0)));
	}
	
	@Test
	public void testWastePileRemoveIsLegalWhenNotEmpty() {
		FortyThieves ft = new FortyThieves();
		ft.addCardToPile("Waste Pile", new Card(1, 1));
		assertTrue(ft.isValidDraw("Waste Pile"));
	}
	
	@Test
	public void testWastePileRemoveIsNotLegalWhenEmpty() {
		FortyThieves ft = new FortyThieves();
		assertFalse(ft.isValidDraw("Waste Pile"));
	}
	
	@Test
	public void testWastePileSizeIncreasesWhenCardAdded() {
		FortyThieves ft = new FortyThieves();
		ft.addCardToPile("Waste Pile", new Card(2, 2));
		assertEquals(1, ft.getNumCardsInPile("Waste Pile"));
	}
	
	@Test
	public void testWastePileAddedCardIsNewTopCard() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(1, 1);
		Card b = new Card(2, 2);
		
		ft.addCardToPile("Waste Pile", a);
		ft.addCardToPile("Waste Pile", b);
		assertEquals(b, ft.getCardInPile("Waste Pile", 0));
	}
	
	@Test
	public void testWastePileSizeDecreasesWhenCardRemoved() {
		FortyThieves ft = new FortyThieves();
		ft.addCardToPile("Waste Pile", new Card(0, 0));
		ft.addCardToPile("Waste Pile", new Card(0, 1));
		ft.removeCardFromPile("Waste Pile");
		assertEquals(1, ft.getNumCardsInPile("Waste Pile"));
	}
	
	@Test
	public void testWastePileNextCardIsTopAfterRemoval() {
		FortyThieves ft = new FortyThieves();
		Card a = new Card(0, 1);
		Card b = new Card(0, 2);
		
		ft.addCardToPile("Waste Pile", a);
		ft.addCardToPile("Waste Pile", b);
		ft.removeCardFromPile("Waste Pile");
		assertEquals(a, ft.getCardInPile("Waste Pile", 0));
	}
}