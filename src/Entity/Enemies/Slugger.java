package Entity.Enemies;

import Entity.*;
import tileMap.TileMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Slugger extends Enemy{
	
	private BufferedImage[] sprites;
	
	public Slugger(TileMap tm){
		
		super(tm);
		
		moveSpeed = 0.3;
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		health = maxHealth = 2;
		damage = 1;
		
		//Load Sprites
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"Sprites/Enemies/slugger.gif"));
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(
						i * width,
						0,
						width,
						height);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
	}
	
	private void getNextPosition() {
		
		//Movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		
		//Falling
		if(falling) {
			dy += fallSpeed;
		}

	}
	
	public void update() {
		
		//Update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		//Check flinching
		if(flinching) {
			long elapsed = 
					(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching =  false;
			}
		}
		
		//If it hits a wall, go other direction
		

	}
	
	public void draw(java.awt.Graphics2D g){
		
		if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
	}

}

















