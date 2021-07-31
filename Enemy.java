import javax.swing.JPanel;
import javax.imageio.*;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.IOException;
import java.util.Random;


public class Enemy {

	BufferedImage enemyFullSprite , enemy;
	
	// 0 means is random
	int difficult = 0;
	
	int enemyType = 1;
	
	int health = 100 , damage = 7;
	int energy = 300;
	
	public int posX = 50,  posY = 300;
	
	boolean attack, defense, rest;
	
	boolean choice;
	
	Random ran = new Random();
	
	int random = 0;
	
	public Enemy () throws IOException {
		 
		switch (enemyType) {
		case 0: enemyFullSprite = ImageIO.read(getClass().getResourceAsStream("/128x80Minotaur_FullSheet.png"));break;
		case 1: enemyFullSprite = ImageIO.read(getClass().getResourceAsStream("/npc.png"));enemy = enemyFullSprite;break;
			
			
		}	
		
	}
	
	public void setPosition (int posX , int posY) {
		
		this.posX = posX;
		this.posY = posY;
		
	}
	
	public void update (Graphics2D g2d) {
		
		if (energy > 300)
			energy = 300;
		if (energy <= 0) {
			energy = 0;
		}
		
		if (main.scene_1.turn == 1) {
			
			
			if (!choice) { 
				
				defense = false;
			
				main.scene_1.timer += 0.1f;
				//thinking
				if (main.scene_1.timer >= 3) {
					
					if (energy >= 100 && energy <=300) { 
						random = ran.nextInt(2);
					}
					if (energy >= 50 && energy <100) {
						random = 1;
					}
					if (energy <= 0) {
						random = 2;
					}
					
					//random = 1;
					
					choice = true;
					main.scene_1.timer = 0;
					
				}
				
			
			}else {
			
			switch (random) {
			case 0: Attack(g2d);break;
			case 1: Defense(g2d);break;
			case 2: Rest();break;
			}
			
		  }
			
		}

	}
	
	public void Attack(Graphics2D g2d) {
	
		main.scene_1.timer += 0.1f;
		 
		if (!attack) {
			
		
				g2d.drawImage(main.scene_1.sword, main.scene_1.player.posX + 150, posY, 100 , 100, null);
				main.scene_1.bloodFX_Enemy.animate(g2d);
				
					
			if (main.scene_1.timer >= 2) {
				//main.scene_1.turn = 0;
				main.scene_1.timer = 0;
				if (main.scene_1.player.defense) {
					main.scene_1.player.health -= damage/2;
				}else{
					main.scene_1.player.health -= damage;
				}
				
				energy-=100;
				attack = true;
				
			}
					
		}else {
			
			if (main.scene_1.timer >= 1) {
				main.scene_1.turn = 0;
				main.scene_1.timer = 0;
				choice = false;
				attack=false;
				main.scene_1.bloodFX_Enemy.Restart(); 
			}
		}
		
	}
	
	public void Defense (Graphics2D g2d) {
	
		defense = true;
		//energy-=50;
		main.scene_1.turn = 0;
		choice = false;
		
		
	}
	
	public void Rest () {
		energy+=100;
		main.scene_1.turn = 0;
		choice = false;
	}
	
}
