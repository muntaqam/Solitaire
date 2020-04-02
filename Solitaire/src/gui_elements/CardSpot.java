package gui_elements;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import gui.MainMenu;
import main.Card;

/**
 * Defines a space for card objects to be placed. {@code JLayeredPane} is the superclass 
 * for this class. Includes functionality which allows cards to be placed spread out or 
 * compacted, and face up or face down.
 * 
 * @author Emery
 */
public class CardSpot extends JLayeredPane {
	/**
	 * Version information.
	 */
	private static final long serialVersionUID = 0;
	/**
	 * Defines the size of all card holding panes.
	 */
	private Dimension compPaneSize;
	/**
	 * {@code offSet} holds the value of how many pixels will be between any two 
	 * successive cards' upper bounds.
	 * 
	 * {@code panes} holds the value corresponding to the current number of panes.
	 */
	private int offSet = 30, panes = 0;
	/**
	 * {@code faceUp} defines whether cards will be displayed face up or face down.
	 * 
	 * {@code spread} defines whether cards will be displayed spread vertically or
	 * in a compact pile.
	 */
	private boolean faceUp = false, spread = false;

	/**
	 * Constructor for the {@code CardSpot} class. This constructor chains to another
	 * of {@code CardSpot}'s constructors before setting {@code faceUp} to the boolean
	 * parameter {@code f}.
	 * 
	 * @param d The size that child panes should be set to.
	 * @param f The value that {@code faceUp} should be set to.
	 * @param s The value that {@code spread} should be set to.
	 */
	public CardSpot(Dimension d, boolean f, boolean s) {
		this(d , s);
		faceUp = f;
	}

	/**
	 * Constructor for the {@code CardSpot} class. Sets the
	 * values of {@code compPaneSize} and {@code spread}. Also alters
	 * the value of {@code offSet} depending on the value of {@code spread}.
	 * 
	 * @param d The size that child panes should be set to.
	 * @param s The value that {@code spread} should be set to.
	 */
	public CardSpot(Dimension d, boolean s) {
		compPaneSize = d;
		spread = s;

		if (!spread)
			offSet = 1;
	}

	/**
	 * Adds a given {@code Card} object to a new {@code JPanel}, then
	 * sets this panel as a child.
	 * 
	 * @param c The {@code Card} object to be added.
	 */
	public void addCard(Card c) {
		JPanel h = new JPanel();
		h.setOpaque(false);
		if(c==null) {h.add(createDisplayImage("green.gif"));}
		else{h.add(createDisplayImage(calcFileName(c.getRank(), c.getSuit())));}
		h.setBounds(0, panes * offSet, compPaneSize.width, compPaneSize.height);
		add(h, new Integer(panes));
		panes++;
	}

	/**
	 * Setter method for {@code faceUp}
	 * 
	 * @param f The value that {@code faceUp} should be set to.
	 */
	public void setFacing(boolean f) {
		faceUp = f;
	}

	/**
	 * Generates the corresponding file name for a card with
	 * a given suit and rank.
	 * 
	 * @param r The rank of the card in question.
	 * @param s The suit of the card in question.
	 * @return The newly generated file name.
	 */
	private String calcFileName(String r, String s) {
		String result = r.substring(0, 1);
		if (result.equals("1"))
			result = "10";

		return (result + s.substring(0, 1)).toLowerCase() + ".gif";
	}

	/**
	 * Creates a new {@code JLabel} holding an image of a card. If
	 * {@code faceUp} is {@code false} then the given file name will be
	 * disregarded and the file representing a card back will be displayed
	 * instead. 
	 * 
	 * @param fileName The name of the file whose representation is desired.
	 * @return A {@code JLabel} containing the specified image.
	 */
	private JLabel createDisplayImage(String fileName) {
		if (!faceUp)
			if (MainMenu.HERTZ_MODE)
				fileName = "cb_Hertz.jpg";
			else if (!fileName.equals("green.gif"))
				fileName = "cb.jpg";

		JLabel retVal = new JLabel();
		java.net.URL imgURL = this.getClass().getResource("../resources/cards/" + fileName);
		if (imgURL == null)
			throw new IllegalArgumentException("Couldn't find file: bin/resources/cards/" + fileName);

		ImageIcon cardImage = new ImageIcon(imgURL);    
		retVal.setIcon(cardImage);
		Dimension d = new Dimension(cardImage.getIconWidth() + 10, cardImage.getIconHeight() + 10);
		retVal.setSize(d);
		retVal.setPreferredSize(d);
		retVal.setMaximumSize(d);
		retVal.setMinimumSize(d);
		return retVal;
	}
}
