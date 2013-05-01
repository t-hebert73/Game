/*
 * Class: Background
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: April 28, 2013
 * Class Description: This class handles the background.
 * 
 * 
 * 
 */
package tileMap;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms){
		
		try{
			//Import resource files into the game
			image = ImageIO.read(
					getClass().getResourceAsStream(s));
			
			moveScale = ms;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void setPosition(double x, double y) {
		//Will reset the background so it doesn't keep moving
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(){
		x += dx;
		y += dy;
	}

	//Draw the background
	public void draw(Graphics2D g){
		g.drawImage(image,  (int)x,  (int)y,  null);
		//Making sure the background fills the screen if it is scrolling automatically
		//To the right
		if(x < 0) {
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
		}
		
		//To the left
		if(x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
		}
	}
}
