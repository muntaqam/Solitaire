package gui;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui_elements.CardSpot;
import main.Card;
import main.LittleSpider;
import main.Observer;

import java.awt.Dimension;

/**
 * Jframe that contains the little spider game
 * @author Maurice
 *
 */


/**
 * Defines graphical user interface for a game of Little Spider solitaire.
 * 
 * @author Maahi
 * @author Maurice
 */



public class littleSpiderGui extends JFrame implements Observer{
	/**
	 * Version information.
	 */
	private static final long serialVersionUID = 0;
	/**
	 * The window which contains {@code mainPanel}
	 */
	private MainMenu mainFrame;
	/**
	 * The main panel on which all content will be displayed.
	 */
	private JPanel mainPanel;

	/**
	 * The allotted space for each instance of {@code CardSpot} which will be
	 * visible on {@code mainPanel}.
	 */
	private Dimension spotD;

	/**
	 * A 2d array of elements used to store card images in their proper positions. 
	 */
	private CardSpot[][] holders;

	/**
	 * The game that the instance of this class is representing on screen.
	 */
	private LittleSpider game;

	/**
	 * instance of HashMap of ArrayLists with corresponding String identifiers. Each ArrayList
	 * represents a pile and the identifier denotes which pile it is.
	 */
	private HashMap<String, ArrayList<Card>> t;



	/**
	 * Constructor for this class. Creates a new game of Little Spider solitaire, initializes
	 * the main panel, then initializes the game area.
	 */

	public littleSpiderGui(MainMenu mm) {

		super("Little Spider");
		game = new LittleSpider();
		t= game.getMap();
		//Maybe unnecessary
		setSize(MainMenu.WINDOW_SIZE);
		mainFrame = mm;
		mainPanel = new JPanel();
		drawGame();
		game.addObserver(this);

		
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Used to generate the elements on screen by reading various pieces of game
	 * data from the corresponding instance. Once the layout has been generated,
	 * it is drawn to the screen.
	 */

	public void drawGame() {

		holders = new CardSpot[2][9];
		spotD = new Dimension(getWidth() / 9, getHeight() / 2);

		mainPanel.removeAll();
		mainPanel.setBackground(MainMenu.TABLE_COLOR);

		mainPanel.setLayout(new GridLayout(2, 9));


		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {

				holders[i][j] = new CardSpot(spotD, true,true);


				mainPanel.add(holders[i][j]);
			}
		}

		for(int i = 0; i <8; i++) {

			addCardsFromPile("tpile" + (i+1), 0, i + 1);


		}


		if(game.getCurrent()!=null) {holders[1][0].addCard(game.getCurrent());
		holders[1][0].addMouseListener(new currentClear(game));
		}
		addCardsFromPile("hpile1", 1, 1);
		addCardsFromPile("hpile2", 1, 3);
		addCardsFromPile("hpile3", 1, 5);
		addCardsFromPile("hpile4", 1, 7);

		mainFrame.revalidate();
	}

	/**
	 * Used to properly add a card from the game data to its corresponding
	 * on screen pile.
	 * 
	 * @param id The identifier of the pile that the card is in.
	 * @param r The layout row of the {@code CardSpot} the card is to be displayed in.
	 * @param c The layout column of the {@code CardSpot} the card is to be displayed in.
	 */

	private void addCardsFromPile(String id, int r, int c) {

		if(t.get(id).isEmpty()==true) {
			Card h=null;
			holders[r][c].addCard(h);
			if(game.getCurrent()!=null) {holders[r][c].addMouseListener(new tpileAddListener(game,id));}
			
		}
		else {

		for(int i=t.get(id).size()-1;i>=0;i--) {

			Card k=t.get(id).get(i);
			holders[r][c].addCard(k);

			if(id.equals("hpile1")||id.equals("hpile2")||id.equals("hpile3")||id.equals("hpile4")&&i==0) {
			if(game.getCurrent()==null) {holders[r][c].addMouseListener(new hpileMouseListener(game,id));}
			else {holders[r][c].addMouseListener(new hpileAddListener(game,id));}}

			
			else {

				
				if(game.getCurrent()==null&&i==0) {holders[r][c].addMouseListener(new tpileMouseListener(game,id));}
				else {if(i==0) {holders[r][c].addMouseListener(new tpileAddListener(game,id));}}

				

			}

		}
			
	}
		


		}



	


	/**
	 *  method removes everything from mainPanel then draws game again.
	 */
	@Override
	public void update() {
		mainPanel.removeAll();
		drawGame();
		// TODO Auto-generated method stub

	}
}


