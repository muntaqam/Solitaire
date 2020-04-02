package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import gui_elements.CardSpot;
import main.Golf;

import java.awt.Dimension;

/**
 * Defines graphical user interface for a game of golf solitaire.
 * 
 * @author Emery
 * @author Alan
 */
public class GolfGUI{
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
	private Golf game;
	
	/**
	 * Constructor for this class. Creates a new game of golf solitaire, initializes
	 * the main panel, then initializes the game area.
	 */
	public GolfGUI(MainMenu mm) {
		game = new Golf();
		mainFrame = mm;
		mainPanel = new JPanel();
		
		drawGame();
	}
	
	/**
	 * Used to deliver the main panel to the main frame of the GUI.
	 * 
	 * @return {@code mainPanel}, which contains all relevant elements to the game's display.
	 */
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
		spotD = new Dimension(MainMenu.WINDOW_SIZE.width / 9, MainMenu.WINDOW_SIZE.height / 2);	
		
		mainPanel.removeAll();
		mainPanel.setBackground(MainMenu.TABLE_COLOR);
		
		mainPanel.setLayout(new GridLayout(2, 9));
		
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 9; j++) {
				if (i == 0)
					holders[i][j] = new CardSpot(spotD, true, true);
				else
					holders[i][j] = new CardSpot(spotD, false);

				mainPanel.add(holders[i][j]);
				}
		
		holders[1][3].setFacing(false);
		holders[1][5].setFacing(true);
		
		for(int i = 0; i < 7; i++) {
			addCardsFromPile("Tableau Pile " + i, 0, i + 1);
			holders[0][i + 1].addMouseListener(new GolfUniversalMouseListener(game, this, "Tableau Pile " + i));
		}
		
		addCardsFromPile("Stock Pile", 1, 3);
		holders[1][3].addMouseListener(new GolfUniversalMouseListener(game, this, "Stock Pile"));
		addCardsFromPile("Homecell Pile", 1, 5);
		holders[1][5].addMouseListener(new GolfUniversalMouseListener(game, this, "Homecell Pile"));
		
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
		for(int i = game.getNumCardsInPile(id) - 1; i >= 0; i--)
			holders[r][c].addCard(game.getCardInPile(id, i));
	}
}
