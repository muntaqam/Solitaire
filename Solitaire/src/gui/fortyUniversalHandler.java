package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.FortyThieves;

public class fortyUniversalHandler implements MouseListener {
	/**
	 * The instance of the current game of Forty Thieves
	 */
	private FortyThieves _game=null;
	/**
	 * The pile identifier of the pile you working with
	 */
	private String _pileId=null;
	/**
	 * The instance of the Forty Thieves gui
	 */
	private FortyThievesGUI _gui=null;
	/**
	 * The of the jframe you will be using to display error Messages
	 */
	private JFrame parent = new JFrame();

	
	/**
	 * The constructor for the universal play handler.Initializes the local variables for 
	 * @param j current instance of forty Thieves
	 * @param h The pile identifier of the pile you are using
	 * @param g current instance of Forty Thieves Gui
	 */
	public fortyUniversalHandler(FortyThieves j,String h,FortyThievesGUI g) {
		_game=j;
		_pileId=h;
		_gui=g;
		
	}
	
	/**
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if(arg0.getButton() == MouseEvent.BUTTON3&&_game.getCurrentCard()!=null) {
            if(_game.easterEgg(_pileId)) {_gui.drawGame();}
            else {JOptionPane.showMessageDialog(parent, "You Have Run Out Of Easter Eggs");}
          }
		
		else {
		if(_game.universalPlay(_pileId)) {
			if (_pileId.equals("Stock Pile"))
				if (!_game.universalPlay("Waste Pile"))
					JOptionPane.showMessageDialog(parent, "Error");
			_gui.drawGame();
		}
		else {
			JOptionPane.showMessageDialog(parent, "Invalid Move");
		}
		
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
