import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.*;
import java.util.List;

public class Player  {
	
	public BufferedImage playerSprite , player;
	public int posX = 50,  posY = 300;
	public int x , y;
	
	int framesNum = 3;
	int curFrame = 0;
	int health = 100 , damage = 7;
	int energy = 300;
	
	boolean attack, defense, rest;
	
	public Player () {
		
		try {
			playerSprite = ImageIO.read(getClass().getResourceAsStream("/Hero.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
		player = playerSprite.getSubimage(0 ,0,  64 - 32, 64 - 32);	
	}
	
	public void setPosition (int posX , int posY) {
		
		this.posX = posX;
		this.posY = posY;
		
	}	
	
	//framesNum is the number of frames the sprite has
	public void switchSprite () {
		
		if (curFrame > framesNum) {
			curFrame = 0;
		}else {
			curFrame ++;
		}
	
		switch (curFrame) {
		
		case 0: player = playerSprite.getSubimage(0,0,64 - 32, 64 - 32);break;
		case 1: player = playerSprite.getSubimage(32,0,64 - 32, 64 - 32);break;
		case 2: player = playerSprite.getSubimage(0,32,64 - 32, 64 - 32);break;
		case 3: player = playerSprite.getSubimage(32,32,64 - 32, 64 - 32);break;
		
		}
		
		
		
		
		
		
	}
	
	
}