package gui;

import java.awt.Dimension;

import java.awt.GridLayout;

import javax.swing.JPanel;

import gui_elements.CardSpot;
import main.FortyThieves;
/**
 * Defines graphical user interface for a game of FortyThieves solitaire.
 * 
 * @author Emery
 * @author Maurice
 * @author maahi
 */
public class FortyThievesGUI {
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
	private FortyThieves game;
	/**
	 * Constructor for this class. Creates a new game of FortyThieves solitaire, initializes
	 * the main panel, then initializes the game area.
	 */
	public FortyThievesGUI(MainMenu mm) {
		game = new FortyThieves();
		mainFrame = mm;
		mainPanel = new JPanel();

		drawGame();
	}
	/**
	 * Used to generate the elements on screen by reading various pieces of game
	 * data from the corresponding instance. Once the layout has been generated,
	 * it is drawn to the screen.
	 */
	public void drawGame() {
		holders = new CardSpot[2][15];
		spotD = new Dimension(MainMenu.WINDOW_SIZE.width / 15, MainMenu.WINDOW_SIZE.height / 2);	

		mainPanel.removeAll();
		mainPanel.setBackground(MainMenu.TABLE_COLOR);

		mainPanel.setLayout(new GridLayout(2, 15));

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 15; j++) {
				if (i == 0)
					holders[i][j] = new CardSpot(spotD, true, true);
				else
					holders[i][j] = new CardSpot(spotD, false);

				mainPanel.add(holders[i][j]);
			}

		holders[1][2].setFacing(false);
		holders[1][3].setFacing(true);

		for(int i = 5; i < 13; i++) {
			holders[1][i].setFacing(true);
			addCardsFromPile("Homecell Pile " + (i - 5), 1, i);
			//holders[1][5].addMouseListener( Put Homecell Pile Mouse Listener Here! );
		}

		for(int i = 0; i < 13; i++) {
			addCardsFromPile("Tableau Pile " + i, 0, i + 1);
			//holders[0][i + 1].addMouseListener( Put Tableau Mouse Listener Here! );
		}




		addCardsFromPile("Stock Pile", 1, 2);
		//holders[1][2].addMouseListener( Put Stock Pile Mouse Listener Here! );
		addCardsFromPile("Waste Pile", 1, 3);
		//holders[1][2].addMouseListener( Put Waste Pile Mouse Listener Here! );

		if(game.getCurrentCard()!=null) {
			holders[1][13].setFacing(true);
			holders[1][13].addCard(game.getCurrentCard());
			holders[1][13].addMouseListener(new FortyClearCurrent(this,game));

		}
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
		if(game.getNumCardsInPile(id)<=0) {
			holders[r][c].addCard(null);
			holders[r][c].addMouseListener( new fortyUniversalHandler(game, id, this));
		}
		for(int i = game.getNumCardsInPile(id) - 1; i >= 0; i--) {
			if(game.getNumCardsInPile(id)<=0) {
				holders[r][c].addCard(null);
			}
			else {holders[r][c].addCard(game.getCardInPile(id, i));}
			if(i==0) {
				holders[r][c].addMouseListener( new fortyUniversalHandler(game, id, this));
			}
		}

	}
	/**
	 * Used to deliver the main panel to the main frame of the GUI.
	 * 
	 * @return {@code mainPanel}, which contains all relevant elements to the game's display.
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
