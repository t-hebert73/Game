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

public class GameStateManager {
	
	//Array list to hold all game states
	private GameState[] gameStates;
	private int currentState; //Index of the game state list
	
	public static final int NUMGAMESTATES = 2;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	
	//Constructor
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
	}
	
	public void loadState(int state) {
		if(state == MENUSTATE) 
			gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
	}
	
	public void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		
		//Not currently using init();
		//gameStates[currentState].init(); //Initialize current gameState
	}
	
	public void update() {
		
		try {
		    gameStates[currentState].update(); //Update current gameState
		}
		catch(Exception e) {}
	}
	
	//set current gameState to draw
	//takes in the image created from GamePanel
	public void draw(java.awt.Graphics2D g) {
		
		//calls draw() of the current game state.
		//the first is MenuState.
		try {
		    gameStates[currentState].draw(g);
		}
		catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k); //set current gameState to keyPressed
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k); //set current gameState to keyReleased
	}

}
