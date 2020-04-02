package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame {
	/**
	 * Version information.
	 */
	private static final long serialVersionUID = 0;
	/**
	 * Predefined constant to be used in all frames as the color of the table.
	 */
	public static final Color TABLE_COLOR = new Color(51, 181, 3);
	/**
	 * Predefined window size that all windows of the program should be set to.
	 * Also used to calculate the size of each {@code CardSpot} in a given game.
	 */
	public static final Dimension WINDOW_SIZE = new Dimension(1200, 800);
	/**
	 * Condition for the easter egg of this game.
	 */
	public static boolean HERTZ_MODE;

	/**
	 * Copy of the local instance to be used in action listeners because
	 * in the context of the action listener {@code this} will be a reference to
	 * the listener itself.
	 */
	private MainMenu me;
	/**
	 * Main panel of this frame. Holds all relevant elements.
	 */
	private JPanel panel;
	/**
	 * Menu bar used to create new instances of golf or little spider or
	 * to exit the program.
	 */
	private JMenuBar menuBar;
	/**
	 * The menu that item which holds the relevant drop down items.
	 */
	private JMenu game;
	/**
	 * The drop down menu items used to trigger a game of solitaire or exit the program.
	 */
	private JMenuItem littleSpider,golf,fortyThieves,exit;
	/**
	 * A custom font to be used throughout the program.
	 */
	private Font f;

	/**
	 * Used to set up the main menu of the program. Creates the menu bar and all its
	 * child components as well as adds action listeners for each of them.
	 * 
	 * @param title The title of the application.
	 */
	public MainMenu(String title) {
		super(title);
		me = this;
		f = new Font("Arial", Font.PLAIN, 28);
		HERTZ_MODE = false;

		UIManager.put("Menu.font", f);
		UIManager.put("MenuItem.font", f);

		panel = new JPanel();
		panel.setBackground(TABLE_COLOR);
		panel.setMinimumSize(WINDOW_SIZE);
		panel.addMouseListener(new EasterEggClickListener());

		menuBar =new JMenuBar();
		game =new JMenu ("New Game");
		littleSpider =new JMenuItem("Play Little Spider");
		golf =new JMenuItem("Play Golf");
		fortyThieves =new JMenuItem("Play Forty Thieves");
		exit =new JMenuItem("Exit");
		

		menuBar.add(game);
		game.add(littleSpider);
		game.add(golf);
		game.add(fortyThieves);
		game.add(exit);
		

		setContentPane(panel);
		setJMenuBar(menuBar);

		golf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GolfGUI g = new GolfGUI(me);
				setContentPane(g.getMainPanel());
				g.drawGame();
			}
		});

		littleSpider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				littleSpiderGui j=new littleSpiderGui(me);
				setContentPane(j.getMainPanel());
				j.drawGame();
				
			}
		});
		
		fortyThieves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FortyThievesGUI g = new FortyThievesGUI(me);
				setContentPane(g.getMainPanel());
				g.drawGame();
			}
		});
		
		
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame[] f = Frame.getFrames();

				for (int i = f.length - 1; i >= 0; i--) {
					f[i].dispose();
				}
			}
		});
	}

	/**
	 * The main. Used to start the program rolling by creating an instance of 
	 * {@code MainMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainMenu mm = new MainMenu("Solitaire");

				mm.setSize(WINDOW_SIZE);
				mm.setVisible(true);
				mm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
