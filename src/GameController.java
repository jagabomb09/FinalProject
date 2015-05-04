import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

//this class controls game logic
public class GameController {
	
	Window window = new Window();
	InputListener inputListener = new InputListener();
	StartButtonListener buttonListener = new StartButtonListener();
	
	Random rand = new Random();
	
	public GameController() {
		
		//window.setTitleScreen();
		window.addKeyListener(inputListener);
		window.setGameScreen();
		
		window.setVisible(true);
		window.setResizable(false);
		
		gameLoop();
	}
	
	void gameLoop() {
	}
	
	void movePlayer(int yDir, int xDir) {
		try {
			if (window.map.mapData[window.player.y + yDir][window.player.x + xDir] != 1) {
				window.player.y += yDir;
				window.player.x += xDir;
				
				for (int i = 0; i < window.map.mobs.size(); i ++) {
					if (window.player.y == window.map.mobs.get(i).y && window.player.x == window.map.mobs.get(i).x) {
						window.map.mobs.get(i).takeDamage(window.player.getPhysicalDamage(), 0);
						window.addInfo("Attacked mob for " + (window.player.might - window.map.mobs.get(i).armour) + " damage");
						
						if (window.map.mobs.get(i).health <= 0) {
							window.map.mobs.remove(i);
							
							if (window.player.xp + window.map.mobs.get(i).fort >= window.player.nextLevelXP)
								window.addInfo("Level up!");
							
							window.player.gainXp(window.map.mobs.get(i).fort);
							
							window.addInfo("+ " + window.map.mobs.get(i).fort + " xp");
						}
							
						
						window.player.y -= yDir;
						window.player.x -= xDir;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException ob) {
			//when player tries to move outside the map
		}
		
		
		//System.out.printf(":%d%n", window.player.x);
	}
	
	class InputListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
			switch(e.getKeyChar()) {
			case 'w': movePlayer(-1, 0);
				break;
			case 'a': movePlayer(0, -1);
				break;
			case 's': movePlayer(1, 0);
				break;
			case 'd': movePlayer(0, 1);
			}
			
			if (window.map.mapData[window.player.y][window.player.x] == 3) {
				if (window.player.y > 30)
					window.player.y = 30;
				
				if (window.player.x > 30)
					window.player.x = 30;
				
				window.map.mobs.clear();
				window.map.setNewMap(window.player.y, window.player.x);
				window.addInfo("You proceed down the stairs lower into the dungeon");
				
			}
			
			for (int i = 0; i < window.map.mobs.size(); i ++) {
				window.map.mobs.get(i).move(window.map.mapData, window.player.y, window.player.x);
				
				if (Math.abs(window.map.mobs.get(i).y - window.player.y) < 2 && Math.abs(window.map.mobs.get(i).x - window.player.x) < 2) {
					window.player.takeDamage(window.map.mobs.get(i).might, window.map.mobs.get(i).intellect);
					window.addInfo("recieved " + (window.map.mobs.get(i).might - window.player.armour) + " physical damage");
				}
			}
			
			window.updateScreen();
		}
		
	}
	
	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.setGameScreen();
		}
		
	}
}
