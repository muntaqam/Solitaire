package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Used to trigger the easter egg for these versions of solitaire.
 * 
 * @author Emery
 */
public class EasterEggClickListener extends MouseAdapter {
	/**
	 * Triggered when the mouse is pressed down. Checks if the press
	 * was a pop up trigger i.e. right click, then triggers the pop up menu.
	 * 
	 * @param arg0 The mouse event that triggered this method.
	 */
	public void mousePressed(MouseEvent arg0) {
		if (arg0.isPopupTrigger())
			createMenu(arg0);
	}

	/**
	 * Triggered when the mouse is released. Checks if the release
	 * was a pop up trigger i.e. right click, then triggers the pop up menu.
	 * 
	 * @param arg0 The mouse event that triggered this method.
	 */
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.isPopupTrigger())
			createMenu(arg0);
	}
	
	/**
	 * Triggered when the a mouse click event is recognized as a pop
	 * up trigger. Creates a pop up menu and adds its action listener.
	 * 
	 * @param arg0 The mouse event that triggered the calling method.
	 */
	public void createMenu(MouseEvent arg0) {
		JPopupMenu jp = new JPopupMenu();
		JMenuItem ji = new JMenuItem("Activate Hertz Mode");
		
		
		ji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.HERTZ_MODE = true;
				jp.setVisible(false);
			}
		});
		
		jp.add(ji);
		jp.show(arg0.getComponent(), arg0.getX(), arg0.getY());
	}
}
