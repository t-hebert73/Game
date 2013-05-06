/*
 * Class: GamePanel
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: April 28, 2013
 * Class Description: This class handles the game info, key presses and game loop.
 * 
 * 
 * 
 */
package main;

import gameState.GameStateManager;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	//Game dimensions 
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	//Game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000/ FPS;
	
	//Image
	private BufferedImage image;
	private Graphics2D g;
	
	//Game state manager
	private GameStateManager gsm;
	
	//Game Constructor
	public GamePanel() { //game panel constructor
		super(); 
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); //Will scale game to 640x480
		setFocusable(true);
		requestFocus();
	}
	
	
	public void addNotify() { //im assuming this gets called automatically when the game panel is created?
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start(); //Starts public void run()
		}
	}
	
	//Initializer
	//creates the image of the gamePanel (has not been drawn).
	public void init() {
		//Set image buffer
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) image.getGraphics(); //g allows you to draw to the image
		running = true;
		
		//Create new GameStateManager
		gsm = new GameStateManager();
	}
	
	//calls init which creates the gameStateManager. Starts game loop.
	//constantly calls update(), draw(), drawToScreen)
	
	public void run() { 
		init();
		
		//Three timers
		long start;
		long elapsed;
		long wait;
		
		
		 //Game loop 
		while(running) {
			
			//Starting timestamp in nano seconds
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5; //fixes negative value throwing exceptions
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace(); //Always put this in a catch to be aware of problems
			}
		}
	}
	
	// constantly calls gamestatemanagers update()
	public void update() { 
		gsm.update(); 
	}
	
	//constantly draws gamestatemanager-gamestate to the image
	public void draw() {
		// gives the image g to the gameStateManager.
		// eventually draws the gameState to the image. Still isn't displayed yet..
		gsm.draw(g);
	}
	
	//constantly called.
	//draws the graphics of the image to the GamePanel(JPanel) which shows up in the Game(JFrame)
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); //draw that bitch so everyone can see
		g2.dispose(); //im assuming this cleans shit
	}
	
	public void keyTyped(KeyEvent key)
	{}
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	
	
}
