import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class SceneCombat extends JPanel implements ActionListener , KeyListener{
	
	//0 player's turn, 1 enemy's turn
	public int turn = 0;
	
	BufferedImage background , fog , menu, blood;
	public Player player;
	public Enemy enemy;
	public BufferedImage sword , shield;
	public int fogX =0 , fogX2 = 0;
	
	boolean enemyChoice;
	
	public Blood bloodFX , bloodFX_Enemy;
	
	Timer t;
	
	int curButton = 0;
	
	float timer;
	
	boolean playerCanAttack = true;
	
	Button attack , defense , rest;
	
	List <Button> buttons;  
	
	public boolean combatEnd;
	
	
	public SceneCombat () {
		
		Timer t = new Timer ( 75, this);
		
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		
		//background load
			try {
				background = ImageIO.read(getClass().getResourceAsStream("/fantasy-512-x-216_full.png"));
				fog = ImageIO.read(getClass().getResourceAsStream("/fog.png"));
				menu = ImageIO.read(getClass().getResourceAsStream("/UI.png"));
				sword = ImageIO.read(getClass().getResourceAsStream("/20190414_042209_Sword.png"));
				shield = ImageIO.read(getClass().getResourceAsStream("/shield.png"));
				//fog2 = ImageIO.read(getClass().getResourceAsStream("/fog.png"));
				enemy = new Enemy();
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		
			player = new Player();
			
			player.setPosition(200, 432);
			enemy.setPosition(800, 432);
			
			buttons = new ArrayList<>();
			
			attack = new Button ("Attack" , 150 , 150 , 1 );
			defense = new Button ("Defense" , 150 , 180 , 2 );
			rest = new Button ("Rest" , 150 , 210 , 3 );
			
			
			bloodFX = new Blood(enemy.posX , enemy.posY);
			bloodFX_Enemy = new Blood(player.posX , player.posY);
			
			buttons.add(attack);
			buttons.add(defense);
			buttons.add(rest);
			
			
			main.win.add(this);
			t.start();
			
	}	
	
	public void paint (Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		g2d.drawImage(background , 0 , 0 , getWidth() , getHeight() , null);
		
		g2d.drawImage(player.player, player.posX , player.posY , 250 , 250 , null);
		g2d.drawImage(enemy.enemy, enemy.posX , enemy.posY , 250 , 250 , null);
		
		//Fog
		g2d.drawImage(fog , fogX , 0 , getWidth() , getHeight() , null);
		g2d.drawImage(fog , fogX - getWidth() , 0 , getWidth() , getHeight() , null);
			
		//Health bars
		g2d.setColor(Color.red);
		g2d.fillRect(player.posX + 65, player.posY - 10, player.health, 10);
		g2d.fillRect(enemy.posX + 65, player.posY - 10, enemy.health, 10);
		
		//Energy bar (player)
		g2d.setColor(Color.yellow);
		g2d.fillRect(player.posX + 50, player.posY - 25, player.energy, 10);
		
		//Cells for energy bar (player)
		g2d.setColor(Color.black);
		g2d.drawRect(player.posX + 50, player.posY - 25, 50, 10);
		g2d.drawRect(player.posX + 100, player.posY - 25, 50, 10);
		g2d.drawRect(player.posX + 150, player.posY - 25, 50, 10);
		g2d.drawRect(player.posX + 200, player.posY - 25, 50, 10);
		g2d.drawRect(player.posX + 250, player.posY - 25, 50, 10);
		g2d.drawRect(player.posX + 300, player.posY - 25, 50, 10);
		
		//Energy bar (Enemy)
				g2d.setColor(Color.yellow);
				g2d.fillRect(enemy.posX + 50, enemy.posY - 25, enemy.energy, 10);
				
				//Cells for energy bar (Enemy)
				g2d.setColor(Color.black);
				g2d.drawRect(enemy.posX + 50, enemy.posY - 25, 50, 10);
				g2d.drawRect(enemy.posX + 100, enemy.posY - 25, 50, 10);
				g2d.drawRect(enemy.posX + 150, enemy.posY - 25, 50, 10);
				g2d.drawRect(enemy.posX + 200, enemy.posY - 25, 50, 10);
				g2d.drawRect(enemy.posX + 250, enemy.posY - 25, 50, 10);
				g2d.drawRect(enemy.posX + 300, enemy.posY - 25, 50, 10);
		
		g2d.drawImage(menu ,80 , 50 , menu.getWidth() / 3 , menu.getHeight() / 3 ,null);
		
		//buttons
		//attack = new Button ("Attack" , 150 , 150);
		attack.setup(g2d);
	
		//defense = new Button ("Defense" , 150 , 180);
		defense.setup(g2d);
		
		//defense = new Button ("Rest" , 150 , 210);
		rest.setup(g2d);
			
		
		if (buttons.get(curButton).click) {
			buttons.get(curButton).onClick(g2d);
		}
		
				
		if (player.attack) {
			
			timer += 0.1f;
			
			g2d.drawImage(sword, enemy.posX + 150, enemy.posY, -100 , 100, null);
			
			
			bloodFX.animate(g2d);
			
			if (timer >= 2) {
				turn = 1;
				player.attack = false;
				bloodFX.Restart();
				timer = 0;
			}
			
		}
		
		if (player.defense) {
			g2d.drawImage(shield, player.posX + 250,player.posY, -100 , 100, null);
		}
		
		if (enemy.defense) {
			
				g2d.drawImage(main.scene_1.shield, enemy.posX + 250, enemy.posY, -100 , 100, null);
			
		}
		
		if (turn == 1) {
			
			enemy.update(g2d);
			main.scene_1.playerCanAttack = true;
			g2d.setColor(Color.red);
			g2d.setFont(new Font ("TimesRoman", Font.PLAIN, 30));
			g2d.drawString("Enemy Turn!", getWidth()/2 - 500, 125);
			g2d.setFont(new Font ("TimesRoman", Font.PLAIN, 100));
			g2d.drawString("Enemy Turn!", getWidth()/2 - 225, 300);
			
		}else {
			g2d.setColor(Color.red);
			g2d.setFont(new Font ("TimesRoman", Font.PLAIN, 30));
			g2d.drawString("Your Turn!", getWidth()/2 - 500, 125);
			g2d.setFont(new Font ("TimesRoman", Font.PLAIN, 100));
			g2d.drawString("Your Turn!", getWidth()/2 - 225, 300);
		}
		
	}
	
	//Main loop
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(timer);
		
		if (!combatEnd) {
			
		if (player.energy >300)
				player.energy = 300;
			
			fogX += 10;
			fogX2+= 10;
			if (fogX >= getWidth())
				fogX = 0;
		
			if (curButton > buttons.size() - 1) {
				
				curButton = 0;
				buttons.get(curButton).selected = true;
				
			}else if (curButton < 0) {
				 
				curButton =  buttons.size() - 1;
				buttons.get(curButton).selected = true;
			}else {
				buttons.get(curButton).selected = true;
			}
			
			
			for (int i = 0; i < buttons.size(); i++) {
				
				if (i != curButton) {
					buttons.get(i).selected = false;
				}
				
			}
			
			if (player.health <= 0) {
				main.lose = true;
				combatEnd = true;
				GAMEOVER();
			}
			
			if (enemy.health <= 0) {
				main.lose = false;
				combatEnd = true;
				GAMEOVER();
						
			}
			
			//System.out.println(turn);
			
			
			//Enemy turn
			
				
			
			 repaint();
		}else {
			
		}
	 }

	@Override
	public void keyTyped(KeyEvent e) {
		
	
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: curButton --; break;
		case KeyEvent.VK_S: curButton ++;break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (turn == 0) {
			
			player.defense = false;
			
			if (playerCanAttack) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER: buttons.get(curButton).click = true; playerCanAttack = false;break;
				}
			}
		}
		
	}
	
	void GAMEOVER () {
		main.ChangeScene(2);
		t = new Timer (75 , this);
		t.stop();
		main.scene_1 = null;
		main.win.remove(this);
	}
	
	
}
