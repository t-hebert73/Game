/*
 * Class: Level1State
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: May 6, 2013
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
import java.util.ArrayList;
import Entity.Enemies.*;
import main.GamePanel;
import Audio.AudioPlayer;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	
	private HUD hud;
	
	private AudioPlayer bgMusic; 
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset2.gif"); //take out the 2 for the old tileset.
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0,0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
		
		player = new Player(tileMap);
		player.setPosition(100,100);
		
		populateEnemies();
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(player);
		
		//Load background music
		bgMusic = new AudioPlayer("/Music/level1.mp3"); // Your dick is small. 8=>
		bgMusic.play();
	}
	
	public void populateEnemies() {
		
        enemies = new ArrayList<Enemy>();
		
        //Fill the map with sluggers at the following points
		Slugger s;
		Point[] points = new Point[] { 
		    new Point(200, 100),
			new Point(860, 200),
			new Point(1525, 200),
			new Point(1680, 200),
			new Point(1800, 200)
		};
		
		for(int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
	}
	
	public void update() {
		player.update();
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety());
		
		//Set Background for scrolling
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		//Attack enemies
		player.checkAttack(enemies);
		
		//Update Enemies
		for(int i = 0; i < enemies.size(); i++) {
			
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
			}
		}
		
		//Update Explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
 	}

	public void draw(Graphics2D g) {
		
		//Draw background
		bg.draw(g);
		
		//Draw tile map
		tileMap.draw(g);
		
		//Draw player
		player.draw(g);
		
		//Draw Enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		//Draw Explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
					(int)tileMap.getx(), (int)tileMap.gety());	
			explosions.get(i).draw(g);
		}
		
		//Draw hud
		hud.draw(g);
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
