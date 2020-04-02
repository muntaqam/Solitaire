package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import main.Card;
import main.LittleSpider;

/**
 * Tests functionality of LittleSpider class.
 * 
 * @author Muntaqa Maahi
 * @author Maurice 
 *
 */
public class LittleSpiderTest {

	LittleSpider g = new LittleSpider();
	Card twoHearts = new Card(0, 1);// 2 of hearts
	Card kingHearts = new Card(0, 12);// king of hearts
	Card twoSpades = new Card(2, 1);// 2 of spades
	Card aceHearts = new Card(0, 0);// ace of hearts
	Card queenHearts = new Card(0, 11);// queen of hearts
	Card fiveSpades = new Card(2, 5);// 5 of spades
	Card fourSpades=new Card(2,4);
	/**
	 * Tests if Tableau pile is holding 6 cards.
	 */
	@Test
	public void TestTableauPileHoldsSixCards() {
		assertEquals(6, g.getNumCardsInPile("tpile1"));
		assertEquals(6, g.getNumCardsInPile("tpile2"));
		assertEquals(6, g.getNumCardsInPile("tpile3"));
		assertEquals(6, g.getNumCardsInPile("tpile4"));
		assertEquals(6, g.getNumCardsInPile("tpile5"));
		assertEquals(6, g.getNumCardsInPile("tpile6"));
		assertEquals(6, g.getNumCardsInPile("tpile7"));
		assertEquals(6, g.getNumCardsInPile("tpile8"));
	}

	/**
	 * Tests if HomeCellPile has one card.
	 */
	@Test
	public void TestHomecellPilesHoldOneCard() {
		assertEquals(1, g.getNumCardsInPile("hpile1"));
		assertEquals(1, g.getNumCardsInPile("hpile2"));
		assertEquals(1, g.getNumCardsInPile("hpile3"));
		assertEquals(1, g.getNumCardsInPile("hpile4"));
	}

	/**
	 * tests if adding card to HomeCell Pile is permissible.
	 */
	@Test
	public void TestLegalHPile() {
		ArrayList<Card> k = new ArrayList<>();
		k.add(aceHearts);
		assertTrue(g.legalHpile(k, twoHearts));
		assertFalse(g.legalHpile(k, fiveSpades));
		assertFalse(g.legalHpile(k, queenHearts));
	}

	/**
	 * tests if Adding card to homeCell pile increases its number of cards
	 */
	@Test
	public void TestAddHPileNumIncrease() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(aceHearts);
		g.addHpile(k, fiveSpades);
		assertEquals(1, k.size());
		g.addHpile(k, twoHearts);
		assertEquals(2, k.size());
	}
	
	/**
	 * Tests if card being added becomes pile's new top card.
	 */
	@Test
	public void TestAddHPileNewCardTop() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(aceHearts);
		assertEquals(aceHearts, k.get(0));
		g.addHpile(k, twoHearts);
		assertEquals(twoHearts, k.get(0));
		assertEquals(aceHearts, k.get(1));
	}

	/**
	 * tests if Removing card from homeCell pile decreases its number of cards
	 */
	@Test
	public void TestRemoveHPileNumDecrease() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(fiveSpades);
		k.add(twoSpades);
		g.removeHpile(k);
		assertEquals(1, k.size());
		g.removeHpile(k);
		assertEquals(1, k.size());		
	}
	
	/**
	 * tests functionality of removing card from HomeCellPile.
	 */
	@Test
	public void TestRemoveHPileNextCardTop() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(fiveSpades);
		g.addHpile(k, fourSpades);
		g.removeHpile(k);
		assertEquals(fiveSpades, k.get(0));
	}
	
	/**
	 * Tests if removing HomeCell pile's top card is legal.
	 * 
	 */
	@Test
	public void TestRemoveLegalH() {
		ArrayList <Card> k = new ArrayList<>();
		assertFalse(g.removeLegalH(k));
		k.add(fiveSpades);
		assertFalse(g.removeLegalH(k));
		k.add(twoSpades);
		assertTrue(g.removeLegalH(k));
	}

	/**
	 * tests that removing a card from a tableau pile decreases its number of cards
	 */
	@Test
	public void TestRemoveTPileNumDecrease() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(fiveSpades);
		k.add(fourSpades);
		g.removeTpile(k);
		assertEquals(1, k.size());
		g.removeTpile(k);
		assertTrue(k.isEmpty());
	}

	/**
	 * tests functionality of removing card from Tableau Pile.
	 */
	@Test
	public void TestRemoveTPileNextCardTop() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(fiveSpades);
		g.addTpile(k, fourSpades);
		g.removeTpile(k);
		assertEquals(fiveSpades, k.get(0));
	}

	/**
	 * tests if removing card from pile is legal.
	 */
	@Test
	public void TestRemoveLegalT() {
		ArrayList <Card> k = new ArrayList<>();
		assertFalse(g.removeLegalT(k));
		k.add(fiveSpades);
		assertTrue(g.removeLegalT(k));
	}

	/**
	 * tests if adding specific card to Tableau Pile is permissible according to
	 * game guidelines
	 */
	@Test
	public void TestAddLegalT() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(twoHearts);
		assertTrue(g.addLegalT(k, aceHearts));
		assertFalse(g.addLegalT(k, fiveSpades));

	}


	/**
	 * tests if adding card to tableau pile increases its number of cards
	 */
	@Test
	public void TestAddTPileNumIncrease() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(twoHearts);
		g.addTpile(k, aceHearts);
		assertEquals(2, k.size());
		g.addTpile(k, twoSpades);
		assertEquals(3, k.size());
	}
	
	/**
	 * tests if an added card becomes tableau piles new top card
	 */
	@Test
	public void TestAddTPileNewCardTop() {
		ArrayList <Card> k = new ArrayList<>();
		k.add(twoHearts);
		g.addTpile(k, aceHearts);
		assertEquals(aceHearts, k.get(0));
		g.addTpile(k, twoSpades);
		assertEquals(twoSpades, k.get(0));
	}
}
