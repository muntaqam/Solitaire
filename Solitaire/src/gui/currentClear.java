package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.LittleSpider;

/**
 * Mouse Listener for clearing the current card selection
 * @author Maurice
 *
 */



public class currentClear implements MouseListener{
	
	/**
	 * The game that the instance of this class is representing on screen.
	 */
	private LittleSpider _game;
	
	
	
	
/**
 * 
 * Constructor for this class initializes @param k to the  game this listener is for.
 * 
 */
	public currentClear(LittleSpider k) {
		_game=k;
	}
	/**
	 * Contains actions to be taken when current card pile  
	 * is clicked.
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		_game.clearCurrent();
		_game.notifyObservers();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
