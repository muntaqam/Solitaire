package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.LittleSpider;

/**
 * Mouse Listener for adding cards to the homecell piles
 * @author Maurice
 *
 */


public class hpileAddListener implements MouseListener {

	/**
	 * The instance that represents the current game of little Spider.
	 */


	private LittleSpider _game;

	/**
	 * The identifier for the pile that the mouse listener instance is responsible
	 * for.
	 */
	private String pileid;
	// ASK
	/**
	 * Instance of JFrame which shows error message
	 */
	private JFrame parent = new JFrame();

	/**
	 * Constructor for mouseListener
	 * 
	 * @param k
	 *            the instance of game this listener is for
	 * @param y
	 *            The identifier of the pile the listener instance is responsible
	 *            for.
	 */
	public hpileAddListener(LittleSpider k, String y) {
		_game = k;
		pileid = y;

	}
	// ASK

	/**
	 * Contains actions to be taken when homeCell pile is clicked. Checks if adding
	 * card is legal, either adds card to HomeCellPile and sets current card to
	 * null, or Pops out error message window.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(_game.getCurrent()==null) {return;}
		if (_game.legalHpile(_game.getMap().get(pileid), _game.getCurrent())) {
			_game.addHpile(_game.getMap().get(pileid), _game.getCurrent());
			_game.setCurrent(null, null);
		} else {
			JOptionPane.showMessageDialog(parent, "Invalid Move");

		}
		// TODO Auto-generated method stub
		_game.notifyObservers();
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
