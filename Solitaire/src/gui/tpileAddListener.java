package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.LittleSpider;
/**
 * MouseListener for adding cards to the tableau piles
 * @author Maurice
 * @author maahi
 *
 */
public class tpileAddListener implements MouseListener {
	
	/**
	 * The instance that represents the current game of little Spider.
	 */
	private LittleSpider _game;
	/**
	 * The identifier for the pile that the mouse listener instance is responsible for.
	 */
	private String pileid;
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
	public tpileAddListener(LittleSpider k,String y) {
		_game=k;
		pileid=y;

	}

	/**
	 * Contains actions to be taken when Tableau pile is clicked. Checks if adding
	 * card is legal, either adds card to Tableau Pile and sets current card to
	 * null, or Pops out error message window if add is not legal.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {


		if(_game.addLegalT(_game.getMap().get(pileid),_game.getCurrent())) {
			//System.out.println("i got clicked");
			_game.addTpile(_game.getMap().get(pileid), _game.getCurrent());
			_game.setCurrent(null, null);


		}else {

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
