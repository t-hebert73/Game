/*
 * Class: Tile
 * Author: Trevor Hebert, Max Saglimbeni
 * 
 * Last Edited: May 4, 2013
 * Class Description: This class handles the tiles.
 * 
 * 
 * 
 */
package tileMap;

import java.awt.image.BufferedImage;
public class Tile {
	
	private BufferedImage image;
	private int type;
	
	//Tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	//Get image & type
	public BufferedImage getImage(){ return image; }
	public int getType() { return type;}

}
