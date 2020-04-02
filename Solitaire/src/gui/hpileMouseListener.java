package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.LittleSpider;
/**
 * Mouse Listener for removing cards from the home cell pile
 * @author Maurice
 *
 */
public class hpileMouseListener implements MouseListener {
	/**
	 * The instance that represents the current game of little Spider.
	 */
	
	private LittleSpider _game;
	/**
	 * The identifier for the pile that the mouse listener instance is responsible for.
	 */
	private String pileid;
	/**
	 * Constructor for mouseListener
	 * 
	 * @param k
	 *            the instance of game this listener is for
	 * @param y
	 *            The identifier of the pile the listener instance is responsible
	 *            for.
	 */
	public hpileMouseListener(LittleSpider k,String y) {
		_game=k;
		pileid=y;
		
	}
	/**
	 * Contains actions to be taken when homeCell pile is clicked. Checks if removing
	 * card is legal, removes card from HomeCellPile and sets current card to
	 * card which was removed.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(_game.getCurrent()==null) {
		if(_game.removeLegalH(_game.getMap().get(pileid))) {
			_game.setCurrent(_game.getMap().get(pileid).get(0),pileid);
			_game.removeHpile(_game.getMap().get(pileid));
			
		}
		}
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
