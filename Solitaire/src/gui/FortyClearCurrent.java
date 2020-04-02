package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.FortyThieves;

public class FortyClearCurrent implements MouseListener{
	/**
	 * GUI which contains the element that this listener is attached to.
	 */
	private FortyThievesGUI _gui=null;
	/**
	 * The game instance which this listener is active for.
	 */
	private FortyThieves _game=null;
	
	/**
	 * Constructor, sets the _gui and _game variables.
	 * 
	 * @param k The GUI that will be stored in _gui.
	 * @param j The game that will be stored in _game.
	 */
	public FortyClearCurrent(FortyThievesGUI k,FortyThieves j) {
		_gui=k;
		_game=j;
	}
	
	/**
	 * Click event that will trigger the current card to be placed back in its original pile.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		_game.clearCurrent();
		_gui.drawGame();
		
	}

	/**
	 * Auto generated method.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Auto generated method.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Auto generated method.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Auto generated method.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
