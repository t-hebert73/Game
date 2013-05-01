/*
 * Class: GameStateManager
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: April 28, 2013
 * Class Description: This class handles the states of the game. 
 * 
 * 
 * 
 */
package gameState;

import java.util.ArrayList;

public class GameStateManager {
	
	//Array list to hold all game states
	private ArrayList<GameState> gameStates;
	private int currentState; //Index of the game state list
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	
	//Constructor
	public GameStateManager() {
		
		//Initialize list
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this)); //Add to gameState list
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init(); //Initialize current gameState
	}
	
	public void update() {
		gameStates.get(currentState).update(); //Update current gameState
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g); //set current gameState to draw
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k); //set current gameState to keyPressed
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k); //set current gameState to keyReleased
		
	}

}
