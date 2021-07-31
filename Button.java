import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.*;


public class Button {

	BufferedImage buttonSprite;
	
	public boolean selected;
	public boolean click;
	
	public int posX , posY;
	public int sizeX = 100, sizeY = 25;
	
	float timer = 0;
	
	String label;
	
	public int ButtonType;
	
	
	Button (String label , int posX , int posY , int type) {
		
		this.label = label;
		this.posX = posX;
		this.posY = posY;
		this.ButtonType = type;
		
	}
	
	public void onClick (Graphics2D g2d) {
		
		switch (ButtonType) {
		default: Play();break;
		case 1: Attack(g2d);break;
		case 2: Defense(g2d);break;
		case 3: Rest(g2d);break;
		}
		
		click = false;
		
	}

	public void setPosition (int posX , int posY) {
		
		this.posX = posX;
		this.posY = posY;
		
	}	
	
	void setup(Graphics2D g2d) {
		
		if (!selected) {
		g2d.setColor(Color.gray);
		g2d.fillRect(posX , posY , sizeX , sizeY);
		
		g2d.setColor(Color.black);
		g2d.drawString(label, posX + 15, posY + 15);
		
		}else {
			g2d.setColor(Color.green);
			g2d.fillRect(posX , posY , sizeX , sizeY);
			
			g2d.setColor(Color.black);
			g2d.drawString(label, posX + 15, posY + 15);
		}
		
		
		
		
	}
	
	public void Attack (Graphics2D g2d) {
		
		if (main.scene_1.player.energy >= 100) {
			System.out.println("Player attack");
			if (main.scene_1.enemy.defense) {
				main.scene_1.enemy.health -= main.scene_1.player.damage/2;
			}else {
				main.scene_1.enemy.health -= main.scene_1.player.damage;
			}
			main.scene_1.player.energy -= 100;
			main.scene_1.player.attack = true;
		} 
	}
	
	public void Defense (Graphics2D g2d)
	{
		if (main.scene_1.player.energy >= 50) {
			System.out.println("Player defense");
			main.scene_1.turn = 1;
			main.scene_1.player.defense = true; 
			//main.scene_1.player.energy -= 50;
		}
		main.scene_1.playerCanAttack = true;
	
	}
	
	public void Rest (Graphics2D g2d) {
		System.out.println("Player rest");
		main.scene_1.player.energy += 100;
		main.scene_1.turn = 1;
		main.scene_1.playerCanAttack = true;
	}
	
	public void Play () {
		
		main.ChangeScene(1);
		main.mainMenu = null;
		
	}
}
