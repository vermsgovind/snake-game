package snake__game;

import javax.swing.JFrame;

public class Gameframe extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Gameframe(){
		this.add(new Snakepanel());
		this.setTitle("Snake Bud");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		
	}
}