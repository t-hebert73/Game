/*
 * Class: Level1State
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: May 4, 2013
 * Class Description: This class handles the level 1 state.
 * 
 * 
 * 
 */

package gameState;

import java.awt.*;
import tileMap.*;
import Entity.*;
import java.awt.event.KeyEvent;

import main.GamePanel;

public class Level1State extends GameState{
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0,0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
		
		player = new Player(tileMap);
		player.setPosition(100,100);
	}
	
	public void update() {
		player.update();
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety());
	}

	public void draw(Graphics2D g) {
		
		//Draw background
		bg.draw(g);
		
		//Draw tile map
		tileMap.draw(g);
		
		//Draw player
		player.draw(g);
	}
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_SPACE) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching(true);
		if(k == KeyEvent.VK_F) player.setFiring(true);
		
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_SPACE) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
		if(k == KeyEvent.VK_R) player.setScratching(false);
		if(k == KeyEvent.VK_F) player.setFiring(false);
	}

}
