/*
 * Class: MenuState
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: April 28, 2013
 * Class Description: This class handles the Game menu.
 * 
 * 
 * 
 */
package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import tileMap.Background;

public class MenuState extends GameState {
	
	//Background
	private Background bg;
	
	//Current Choice
	private int currentChoice = 0;
	
	//Menu
	private String[] options = {
			"Start",
			"Help",
			"Quit"
	};
	
	//Fonts for the menu
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	//Constructor
	//called first(obv) 
	//creates background with titles.
	public MenuState(GameStateManager gsm) { 
		
		this.gsm = gsm;
		
		try {
			//Set the background
			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);
			
			//Set the title color
			titleColor = new Color(128,0,0);
			
			//Set the title font
			titleFont = new Font(
					"Century Gothic",
					Font.PLAIN, 28);
			
			//Set the regular font
			font = new Font("Arial", Font.PLAIN, 12);
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void init() {}
	
	//Update the background
	public void update() {
		bg.update();
	}
	
	//draws to the image which comes from GamePanel
	//draws the background to the image
	//
	public void draw(Graphics2D g) {
		//Draw background
		bg.draw(g);
		
		//Draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Zombie Killer", 80, 70);
		
		//Draw menu options
		g.setFont(font);
		for(int i=0; i < options.length; i++) {
			if(i == currentChoice){
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}
	}
	private void select() {
		if(currentChoice == 0) {
			//Start
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if(currentChoice == 1) {
			//Help
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	//Handle key events
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select(); // enter = select
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--; // Move up the menu
			if(currentChoice == -1) {
				currentChoice = options.length - 1;  
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++; // Move down the menu
			if(currentChoice == options.length) {
				currentChoice = 0; 
			}
			
		}
	}
	public void keyReleased(int k) {}
}
