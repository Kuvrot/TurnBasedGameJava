import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

public class EndScreen extends JPanel implements ActionListener, KeyListener{

	Timer t = new Timer (5 , this);
	
	EndScreen (){
		
		addKeyListener(this);
		setFocusable(true);
		
		t.start();
		main.win.add(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER : main.ChangeScene(0); t.stop(); main.winScreen = null; main.win.remove(this); break;
		}
			
		
	}
	
	public void paint (Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.red);
		
		g2d.setFont(new Font ("TimesRoman", Font.PLAIN, 30));
		
		if (!main.lose) {
			g2d.drawString("You Win", getWidth()/2 - 150 , getHeight()/2);
		}else {
			g2d.drawString("You Died", getWidth()/2 - 150 , getHeight()/2);
		}
		
		g2d.drawString("Press ENTER to continue", getWidth()/2 - 150 , getHeight()/2 + 50);
		
		
	}

}
