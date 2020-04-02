package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Card;
import main.Golf;
import exceptions.InvalidCardOriginException;
import exceptions.CardMismatchException;
import exceptions.SolitaireException;

/**
 * A mouse event listener designed to provide functionality to any and all clicks on
 * any pile in a given game of golf solitaire.
 * 
 * @author Emery
 * @author Alan
 */
public class GolfUniversalMouseListener implements MouseListener {
	/**
	 * The identifier for the pile that the mouse listener instance is responsible for.
	 */
	private String pileId;
	/**
	 * The instance that represents the current game of golf.
	 */
	private Golf game;
	/**
	 * The GUI instance being used for the current game of golf.
	 */
	private GolfGUI gui;

	/**
	 * Constructor for the mouse listener. Only used for initializing
	 * variables.
	 * 
	 * @param g The instance of {@code Golf} this listener is for.
	 * @param gg The GUI instance being used to display the game.
	 * @param id The identifier of the pile the listener instance is responsible for.
	 */
	public GolfUniversalMouseListener(Golf g, GolfGUI gg, String id) {
		game = g;
		gui = gg;
		pileId = id;
	}

	/**
	 * Contains actions to be taken when the pile this listener is responsible for
	 * is clicked. Either results in a card's movement or in an error message depending
	 * on the context of the click.
	 * 
	 * @param arg0 The mouse event that triggered this method call.
	 */
	public void mouseClicked(MouseEvent arg0) {
		Card c = null;
		try {
			if (game.isValidDraw(pileId))
				c = game.getCardInPile(pileId, 0);
			else
				throw new InvalidCardOriginException();
			
			if (game.isValidPlay(pileId, "Homecell Pile", c)) {
				game.removeCardFromPile(pileId);
				game.addCardToPile("Homecell Pile", c);
			}
			else
				throw new CardMismatchException();
			
			gui.drawGame();
		}catch (SolitaireException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getErrorMessage());
		}
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	public void mouseEntered(MouseEvent arg0) {
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	public void mousePressed(MouseEvent arg0) {
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	public void mouseReleased(MouseEvent arg0) {
	}
}
