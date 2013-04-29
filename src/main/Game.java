//Msaglimbeni is a cunt, Updated: forever;

package main;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		javax.swing.JFrame window = new JFrame("Zombie Killer");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}
