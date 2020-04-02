package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.InvalidPileIdentifierException;

public class FortyThieves {
	/**
	 * Hashmap of Arraylist we use to hold card piles
	 */
	private Map<String, ArrayList<Card>> pileLookupPairs;
	/**
	 * List of all cards we will be using during the game
	 */
	private List<Card> allCards;
	/**
	 * The two decks of cards we will start the game with
	 */
	private Deck d1, d2;
	/**
	 * The current card we have selected
	 */
	private Card currentChoice = null;
	/**
	 * String id of pile we took the current card from
	 */
	private String previousPile = null;
	/**
	 * Variable used to count how many times the easter egg has been used
	 */
	private int easterEggCnt=0;
	
	
	
	/** sets up hashmap of card piles
	 */
	private void initHashMap() {
		pileLookupPairs = new HashMap<String, ArrayList<Card>>();
		pileLookupPairs.put("Stock Pile", new ArrayList<Card>());
		pileLookupPairs.put("Waste Pile", new ArrayList<Card>());
		for (int i = 0; i < 8; i++)
			pileLookupPairs.put("Homecell Pile " + i, new ArrayList<Card>());
		for (int i = 0; i < 13; i++)
			pileLookupPairs.put("Tableau Pile " + i, new ArrayList<Card>());
	}
	
	/**
	 * constructor for fortythieves.initializes piles and hashmap of piles
	 */
	public FortyThieves() {
		initHashMap();
		
		d1 = new Deck();
		d2 = new Deck();
		d1.shuffle();
		d2.shuffle();
		
		allCards = new ArrayList<Card>();
		for (int i = 0; i < 52; i++) {
			allCards.add(d1.get(i));
			allCards.add(d2.get(i));
		}
		
		int currentHomecell = 0;
		for(int i = 0; i < 13; i++) {
			ArrayList<Card> currentPile = pileLookupPairs.get("Tableau Pile " + i);
			while(currentPile.size() < 3) {
				Card currentCard = allCards.remove(allCards.size() - 1);
				if(currentCard.getRank().equals("Ace")) {
					pileLookupPairs.get("Homecell Pile " + currentHomecell).add(currentCard);
					currentHomecell += 1;
				} else
					currentPile.add(currentCard);
			}
		}
		
		while(!allCards.isEmpty()) {
			Card currentCard = allCards.remove(allCards.size() - 1);
			if (currentCard.getRank().equals("Ace")) {
				pileLookupPairs.get("Homecell Pile " + currentHomecell).add(currentCard);
				currentHomecell += 1;
			} else
				pileLookupPairs.get("Stock Pile").add(currentCard);
		}
	}
	
	/**
	 * Gets the number of cards in pile
	 * @param pileId 
	 * @return number of cards in pile
	 */
	public int getNumCardsInPile(String pileId) {
		if (!isValidId(pileId))
			throw new InvalidPileIdentifierException();
		
		return pileLookupPairs.get(pileId).size();
	}
	
	/**
	 * Checks if the current play is valid or not
	 * @param originId id of pile the card originated from
	 * @param destinId id of pile you want to add to
	 * @param c card you want to add to the pile
	 * @return return true or false 
	 */
	public boolean isValidPlay(String originId, String destinId, Card c) {
		if (!isValidId(originId) || !isValidId(destinId))
			throw new InvalidPileIdentifierException();
		
		String pileTD = pileType(destinId);
		ArrayList<Card> des = pileLookupPairs.get(destinId);
		
		if (pileType(originId).equals("Stock"))
			if (pileTD.equals("Waste"))
				return true;
			else
				return false;
		if (des.isEmpty())
			if (pileTD.equals("Tableau"))
				return true;
			else
				return false;
		
		Card oldTop = des.get(0);
		
		if (pileTD.equals("Home"))
			if (oldTop.getSuit().equals(c.getSuit()) && c.getValue() == oldTop.getValue() + 1)
				return true;
		if (pileTD.equals("Tableau"))
			if (oldTop.getSuit().equals(c.getSuit()) && c.getValue() == oldTop.getValue() - 1)
				return true;
		
		return false;
	}
	
	/**
	 * Checks if removing a card is valid
	 * @param pileId id of pile you would like to remove a card from
	 * @return true or false is play is valid
	 */
	public boolean isValidDraw(String pileId) {
		if (!isValidId(pileId))
			throw new InvalidPileIdentifierException();
		
		if(pileType(pileId).equals("Home") || pileLookupPairs.get(pileId).isEmpty())
			return false;
		
		return true;
	}
	
	/**
	 * Returns the card at the specific location
	 * @param pileId pile id of pile
	 * @param i the location of the card you want
	 * @return card at the specific location
	 */
	public Card getCardInPile(String pileId, int i) {
		if (!isValidId(pileId))
			throw new InvalidPileIdentifierException();
		
		return pileLookupPairs.get(pileId).get(i);
	}
	
	/**
	 * Removes the top card from pile and sets currentchoice and previous pile
	 * @param pileId pile id of pile you want to remove card from
	 */
	public void removeCardFromPile(String pileId) {
		if(!isValidId(pileId))
			throw new InvalidPileIdentifierException();
			
		previousPile = pileId;
		currentChoice = pileLookupPairs.get(pileId).remove(0);
	}
	
	/**
	 * adds card to top of pile
	 * @param pileId id of pile you would like to add card to
	 * @param c card you want to add to the top off the card
	 */
	public void addCardToPile(String pileId, Card c) {
		if(!isValidId(pileId))
			throw new InvalidPileIdentifierException();
		
		pileLookupPairs.get(pileId).add(0, c);
		currentChoice = null;
		previousPile = null;	
	}
	
	/**
	 * Checks if the pile id is valid 
	 * @param id id of pile you are checking
	 * @return true or false
	 */
	public boolean isValidId(String id) {
		return (id != null && pileLookupPairs.containsKey(id));
	}
	
	/**
	 * Adds the current card back to the previous pile and sets previousPile and current choice to null
	 */
	public void clearCurrent() {
		pileLookupPairs.get(previousPile).add(0,currentChoice);
		previousPile = null;
		currentChoice = null;
	}
	
	/**
	 * Universal method for adding and removing card from piles
	 * @param pileId id of pile you want to add or remove from
	 * @return true if the move is valid or ot
	 */
	public boolean universalPlay(String pileId) {
		
		if(currentChoice == null)
			if (isValidDraw(pileId)) {
				removeCardFromPile(pileId);
				return true;
			}
			else
				return false;
		if (isValidPlay(previousPile, pileId, currentChoice)) {
			addCardToPile(pileId, currentChoice);
			return true;
		
		}
		return false;
	}
	
	/**
	 * Checks what type of pile you have and returns it
	 * @param pileid id of pile you are working with
	 * @return String of what type of pile it is
	 */
	public String pileType(String pileid) {
		if(pileid.charAt(0)=='H') {return "Home";}
		else if(pileid.charAt(0)=='T') {return "Tableau";}
		else if(pileid.charAt(0)=='W') {return "Waste";}
		else return "Stock";
	}
	
	/**
	 * Returns what the current card is
	 * @return the current card choice 
	 */
	public Card getCurrentCard() {
		return currentChoice;
	}
	
	/**
	 * Easter Egg function that allows you to add a card to any pile you would like without
	 * following rule of game
	 * @param pileid
	 * @return
	 */
	public boolean easterEgg(String pileid) {
		if(easterEggCnt<2&&currentChoice!=null) {
			easterEggCnt++;
			addCardToPile(pileid,currentChoice);
			return true;
			
		}
		return false;
	}
	
}