/*
 * Class: GameState
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: April 28, 2013
 * Class Description: I don't fully understand how abstract classes work but I know its used for subclassing. lol
 * 
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
