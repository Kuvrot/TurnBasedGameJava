import javax.imageio.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Blood {
	
	//frames
	BufferedImage f1 , f2 , f3 , f4 , f5 , f6 , f7 , f8, f9;
	
	int curFrame = 0;
	
	int posX , posY ;
	
	boolean play = true;
	
	Blood (int posX , int posY) {
		
		this.posX = posX;
		this.posY = posY;
		
		try {
			
			f1 =  ImageIO.read(getClass().getResourceAsStream("/blood_1.png"));
			f2 =  ImageIO.read(getClass().getResourceAsStream("/blood_2.png"));
			f3 =  ImageIO.read(getClass().getResourceAsStream("/blood_3.png"));
			f4 =  ImageIO.read(getClass().getResourceAsStream("/blood_4.png"));
			f5 =  ImageIO.read(getClass().getResourceAsStream("/blood_5.png"));
			f6 =  ImageIO.read(getClass().getResourceAsStream("/blood_6.png"));
			f7 =  ImageIO.read(getClass().getResourceAsStream("/blood_7.png"));
			f8 =  ImageIO.read(getClass().getResourceAsStream("/blood_8.png"));
			f9 =  ImageIO.read(getClass().getResourceAsStream("/blood_9.png"));
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void animate (Graphics2D g2d)
	{
		switch (curFrame) {
		
		case 0: g2d.drawImage(f1, posX, posY, 192, 187 ,null);break;
		case 1: g2d.drawImage(f2, posX, posY, 192, 187 ,null);break;
		case 2: g2d.drawImage(f3, posX, posY, 192, 187 ,null);break;
		case 3: g2d.drawImage(f4, posX, posY, 192, 187 ,null);break;
		case 4: g2d.drawImage(f5, posX, posY, 192, 187 ,null);break;
		case 5: g2d.drawImage(f6, posX, posY, 192, 187 ,null);break;
		case 6: g2d.drawImage(f7, posX, posY, 192, 187 ,null);break;
		case 7: g2d.drawImage(f8, posX, posY, 192, 187 ,null);break;
		case 8: g2d.drawImage(f9, posX, posY, 192, 187 ,null);break;

		
		}
		if (play)
			curFrame++;
		
	}
	
	public void Restart () {
		
		curFrame=0;
		
	}
}
