import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import java.util.List;
import javax.swing.Timer;
import java.util.ArrayList;

public class MainMenu extends JPanel implements ActionListener , KeyListener{
	
	Button Play;
	Button Exit;
	
	List <Button> buttons;
	
	int curButton = 0;
	
	Timer t = new Timer (5 , this);
	
	MainMenu (){
		
		addKeyListener(this);
		setFocusable(true);
		
		
		buttons = new ArrayList ();
		Play = new Button ("PLAY" , getWidth()/2 , getHeight()/2 , 0);
		Exit = new Button ("EXIT" , getWidth()/2 , getHeight()/2 , 4);
		
		buttons.add(Play);
		buttons.add(Exit);
		
		main.win.add(this);
		
		t.start();
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (curButton >= buttons.size()) {
			curButton = 0;
		}else if (curButton < 0) {
			curButton = buttons.size()-1;
		}
		
		buttons.get(curButton).selected = true;
		
		for (int i = 0; i < buttons.size();i++) {
			if (curButton != i) {
				buttons.get(i).selected = false;
			}
		}
		
		System.out.println(curButton);
		
		repaint();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_W : curButton--;break;
		case KeyEvent.VK_S : curButton++;break;
		
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
			
			if (curButton == 0) {
				buttons.get(curButton).Play();
				t.stop();
				main.win.remove(this);
			}else {
				
				System.exit(0);
				
			}
			
		}
		
	}

	public void paint (Graphics g) {
	
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		Play.setPosition(getWidth()/2, getHeight()/2);
		Play.setup(g2d);
		Exit.setPosition(getWidth()/2, (getHeight()/2) + 30);
		Exit.setup(g2d);
		
		
	}
	
	
	
	
}
