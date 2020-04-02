package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the solitaire game "Little Spider" {@code ArrayList} represents
 * card piles. {@code tpile(n)} represents Tableau Piles. {@code hpile(n)}
 * represent HomeCell piles.
 *
 * @author Muntaqa Maahi
 *
 */
public class LittleSpider {

	/**
	 * creating instance of{@code ArrayList<Card>} Tableau and HomeCell piles.
	 * {@code map} creating instance of a hashMap of tableau piles.
	 */

	private ArrayList<Card> tpile1 = new ArrayList<>();
	private ArrayList<Card> tpile2 = new ArrayList<>();
	private ArrayList<Card> tpile3 = new ArrayList<>();
	private ArrayList<Card> tpile4 = new ArrayList<>();
	private ArrayList<Card> tpile5 = new ArrayList<>();
	private ArrayList<Card> tpile6 = new ArrayList<>();
	private ArrayList<Card> tpile7 = new ArrayList<>();
	private ArrayList<Card> tpile8 = new ArrayList<>();

	private ArrayList<Card> hpile1 = new ArrayList<>();
	private ArrayList<Card> hpile2 = new ArrayList<>();
	private ArrayList<Card> hpile3 = new ArrayList<>();
	private ArrayList<Card> hpile4 = new ArrayList<>();
	private HashMap<String, ArrayList<Card>> map = new HashMap<String, ArrayList<Card>>();
	private Card currentSelection = null;
	private String previd = null;
	private ArrayList<Observer> _observers = new ArrayList<>();

	/**
	 * {@constructor} Sets Tableau Piles and HomeCell piles according to
	 * littleSpider guidelines and triggers the method to initialize {@code map}.
	 */
	public LittleSpider() {
		Deck l = new Deck();
		l.shuffle();
		List<Card> K = l.getCopy();
		// sets home piles
		for (int i = K.size() - 1; i >= 0; i--) {
			Card u = K.get(i);
			if (u.getRank().equals("Ace") && u.getSuit().equals("Diamonds")) {
				hpile1.add(u);
				K.remove(i);
			} else if (u.getRank().equals("Ace") && u.getSuit().equals("Hearts")) {
				hpile2.add(u);
				K.remove(i);
			} else if (u.getRank().equals("King") && u.getSuit().equals("Clubs")) {
				hpile3.add(u);
				K.remove(i);
			} else if (u.getRank().equals("King") && u.getSuit().equals("Spades")) {
				hpile4.add(u);
				K.remove(i);
			}
		}
		// sets tpiles
		for (int i = K.size() - 1; i >= 0; i--) {
			if (i < 6) {
				tpile1.add(K.get(i));
			} else if (i < 12 && i >= 6) {
				tpile2.add(K.get(i));
			} else if (i < 18 && i >= 12) {
				tpile3.add(K.get(i));
			} else if (i < 24 && i >= 18) {
				tpile4.add(K.get(i));
			} else if (i < 30 && i >= 24) {
				tpile5.add(K.get(i));
			} else if (i < 36 && i >= 30) {
				tpile6.add(K.get(i));
			} else if (i < 42 && i >= 36) {
				tpile7.add(K.get(i));
			} else if (i < 48 && i >= 42) {
				tpile8.add(K.get(i));
			}
		}

		initializeMap();
	}

	/**
	 * Initializes {@code map} to hold a reference to each pile with a corresponding
	 * String identifier.
	 */
	public void initializeMap() {
		map.put("tpile1", tpile1);
		map.put("tpile2", tpile2);
		map.put("tpile3", tpile3);
		map.put("tpile4", tpile4);
		map.put("tpile5", tpile5);
		map.put("tpile6", tpile6);
		map.put("tpile7", tpile7);
		map.put("tpile8", tpile8);
		map.put("hpile1", hpile1);
		map.put("hpile2", hpile2);
		map.put("hpile3", hpile3);
		map.put("hpile4", hpile4);
	}

	public HashMap<String, ArrayList<Card>> getMap() {
		return map;
	}

	/**
	 * Method used to get the number of cards in a given pile.
	 * 
	 * @return The number of cards in the pile.
	 * @return {@code -1} if the identifier is invalid.
	 */
	public int getNumCardsInPile(String id) {
		if (map.containsKey(id))
			return map.get(id).size();
		return -1;
	}

	/**
	 * adds appropriate cards to Tableau Piles . {@code addLegalT} checks if card is
	 * appropriate to add to Tableau Pile.
	 * 
	 * @param g
	 *            Specific card in @param F ArrayList of Cards.
	 */

	public void addTpile(ArrayList<Card> F, Card g) {
		if (addLegalT(F, g))
			F.add(0, g);

	}

	/**
	 * checks if adding a specific card to Tableau Pile is allowed.
	 * 
	 * @param g
	 *            Specific card in @param F ArrayList of Cards
	 * @return true if card satisfies the requirement to add to Tableau Pile.
	 */


	public  boolean addLegalT(ArrayList<Card> F, Card g) {
		
		if(F.isEmpty()==true) {
			return true;
		}
		else if (F.get(0).getValue() == 0) {

			if (g.getValue() == 1 || g.getValue() == 12) {
				return true;
			}
		}
		else if (F.get(0).getValue() == 12) {
			if (g.getValue() == 0 || g.getValue() == 11) {
				return true;
				
			}

		}
		else if (g.getValue() == F.get(0).getValue() - 1 || g.getValue() == F.get(0).getValue() + 1 ) {

		
			return true;
		}

		return false;
	}

	/**
	 * Checks if there are any cards in the pile
	 * 
	 * @param k
	 *            ArrayList of Cards
	 * @return true if pile contains any cards.
	 */
	public boolean removeLegalT(ArrayList<Card> k) {
		return !k.isEmpty();
	}

	/**
	 * removes top card from Tableau Pile
	 * 
	 * @param k
	 *            ArrayList of Card
	 */

	public void removeTpile(ArrayList<Card> k) {
		if (removeLegalT(k)) {
			k.remove(0);
		}

	}

	/**
	 * Checks if there are any cards in the pile
	 * 
	 * @param k
	 *            ArrayList of Cards
	 * @return true if pile contains any cards.
	 */

	public boolean removeLegalH(ArrayList<Card> k) {
		return k.size() > 1;
	}

	/**
	 * removes the top card from HomeCellPile.
	 * 
	 * @param k
	 *            ArrayList of Card
	 */

	public void removeHpile(ArrayList<Card> k) {
		if (removeLegalH(k)) {
			k.remove(0);

		}

	}

	/**
	 * checks if it is legal to add card.
	 * 
	 * @param J
	 *            the homeCell pile
	 * @param t
	 *            the card being added
	 * @return true if it is legal to add card
	 */

	public boolean legalHpile(ArrayList<Card> J, Card t) {
		if (t.getSuit().equals("Diamonds") || t.getSuit().equals("Hearts")) {
			if (t.getSuit().equals(J.get(0).getSuit()) && J.get(0).getValue() + 1 == t.getValue()) {
				return true;
			}
		} else if (t.getSuit().equals("Clubs") || t.getSuit().equals("Spades")) {
			if (t.getSuit().equals(J.get(0).getSuit()) && J.get(0).getValue() - 1 == t.getValue()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * adds card to home pile if legal
	 * 
	 * @param J
	 *            the homecell pile
	 * @param v
	 *            the card to be added
	 */
	public void addHpile(ArrayList<Card> J, Card v) {
		if (legalHpile(J, v))
			J.add(0, v);

	}

	public Card getCurrent() {
		return currentSelection;
	}

	public void setCurrent(Card g, String k) {
		currentSelection = g;
		setPrev(k);

	}

	public void addObserver(Observer obs) {
		_observers.add(obs);
		notifyObservers();
	}

	public void notifyObservers() {
		for (Observer obs : _observers) {
			obs.update();
		}
	}

	public void clearCurrent() {

		map.get(previd).add(0, currentSelection);
		setCurrent(null, null);

	}

	public void setPrev(String id) {
		previd = id;

	}

	public String getPrev() {
		return previd;
	}

}
