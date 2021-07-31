import javax.swing.JFrame;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;


public class main extends JFrame{
	
	static JFrame win;
	static int SceneNumber = 0;
	
	static int curScene = 0;
	static SceneCombat scene_1;
	static MainMenu mainMenu;
	static EndScreen winScreen;
	
	public static boolean lose = false;
	
	public static void main (String[] args) {
			
		win = new JFrame("ARENA");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(1280 , 720);
		win.setResizable(true);
		
		switch (curScene) {
		case 0: mainMenu = new MainMenu ();break;
		case 1: scene_1 = new SceneCombat(); break;
		case 2: winScreen = new EndScreen();break;
		}
		
		win.setVisible(true);
		
	}
	
	static void ChangeScene (int nextScene) {

		
		curScene = nextScene;
		
		switch (curScene) {
			case 0: mainMenu = new MainMenu ();  DestroyAllScenes(0); break;  //mainMenu.setVisible(true);break;
			case 1: scene_1 = new SceneCombat(); DestroyAllScenes(1); break;
			case 2: winScreen = new EndScreen(); DestroyAllScenes(2); break;
		 
		}
		
	}
	
	static void DestroyAllScenes (int except) {
		
		if (except != 0) {
			if (mainMenu != null) {
				mainMenu.setVisible(false);
			}
		}
		
		if (except != 1) {
			if (scene_1 != null) {
			 scene_1.setVisible(false);	
			}	
		}
		
		if (except != 2) {
			if (winScreen != null) {
			 winScreen.setVisible(false);	
			 }
		}
	}
}
