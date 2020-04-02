package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.LittleSpider;
/**
 * Mouse listener removing cards from the tableau pile
 * @author Maurice
 * @author maahi
 *
 */
public class tpileMouseListener implements MouseListener{
	/**
	 * The instance that represents the current game of Little Spider.
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
	public tpileMouseListener(LittleSpider k,String y) {
		_game=k;
		pileid=y;
		
	}
	/**
	 * Contains actions to be taken when Tableau pile is clicked. Checks if removing
	 * card is legal, either removes card from Tableau Pile and sets current card to
	 *  card that is being removed, or Pops out error message window if remove is not legal.
	 */
	@Override
	public void mouseClicked(MouseEvent argo) {
		
		if(_game.getCurrent()==null) {
			if(_game.removeLegalT(_game.getMap().get(pileid))){
			_game.setCurrent(_game.getMap().get(pileid).get(0),pileid);
			_game.removeTpile(_game.getMap().get(pileid));
			}
		}
			
		
		_game.notifyObservers();
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Auto-generated method header. Necessary for implementation of
	 * {@code MouseListener} interface but does nothing in this context.
	 */
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
