/*
 * Class: Game
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: April 28, 2013
 * Class Description: This class handles the game window.
 * 
 * 
 * 
 */

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
