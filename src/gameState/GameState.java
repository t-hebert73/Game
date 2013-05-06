/*
 * Class: GameState
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: May 06, 2013
 * Class Description: This class is the abstract class for the gameState
 *                    This will be the base of the gameStateManager
 * 
 * 
 */
package gameState;

public abstract class GameState {
	
	//Reference to GameStateManager so it can change its state
	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}
