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
	}
	
	void useItem(int index) {
		if (window.player.inventory[index] != null) {
			window.addInfo("using " + window.player.inventory[index].getName());
			
			switch (window.player.inventory[index].getName()) {
			case "Health Potion": window.player.health += 25;
				if (window.player.health > (window.player.fort * 10))
					window.player.health = (window.player.fort * 10);
				break;
			case "Might Potion": window.player.might += 2;
				break;
			}
			
			window.player.inventory[index] = null;
		}
		
		
	}
	
	void movePlayer(int yDir, int xDir) {
		try {
			if (window.map.mapData[window.player.y + yDir][window.player.x + xDir] != 1) {
				window.player.y += yDir;
				window.player.x += xDir;
				
				for (int i = 0; i < window.map.mobs.size(); i ++) {
					if (window.player.y == window.map.mobs.get(i).y && window.player.x == window.map.mobs.get(i).x) {
						
						int roll = rand.nextInt(7) - 3;
						window.map.mobs.get(i).takeDamage(window.player.getPhysicalDamage() + roll);
						window.addInfo("Attacked mob for " + (window.player.might - window.map.mobs.get(i).armour + roll) + " damage : roll (" + (roll + 3) + ")");
						
						if (window.map.mobs.get(i).health <= 0) {
							
							if (window.player.xp + window.map.mobs.get(i).fort >= window.player.nextLevelXP)
								window.addInfo("Level up!");
							
							window.player.gainXp(window.map.mobs.get(i).fort);
							window.addInfo("+ " + window.map.mobs.get(i).fort + " xp");
							
							window.map.mobs.remove(i);
						}
							
						
						window.player.y -= yDir;
						window.player.x -= xDir;
					}
				}
				
				if (window.map.mapData[window.player.y][window.player.x] == 4) {
					window.map.mapData[window.player.y][window.player.x] = 2;
					int itemType = rand.nextInt(2);
					
					switch(itemType) {
					case 0: window.player.addToInventory(new HealthPot());
					break;
					case 1: window.player.addToInventory(new MightPot());
					break;
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
				break;
			case '1': useItem(0);
				break;
			case '2': useItem(1);
				break;
			case '3': useItem(2);
				break;
			case '4': useItem(3);
				break;
			case '5': useItem(4);
				break;
			case '6': useItem(5);
				break;
			case '7': useItem(6);
				break;
			case '8': useItem(7);
				break;
			case '9': useItem(8);
				break;
			}
			
			if (window.map.mapData[window.player.y][window.player.x] == 3) {
				if (window.player.y > 30) //this keeps the starting room away from the wall to avoid errors
					window.player.y = 30;
				
				if (window.player.x > 30)
					window.player.x = 30;
				
				if (window.player.y < 2)
					window.player.y = 2;
				
				if (window.player.x < 2)
					window.player.x = 2;
				
				window.map.mobs.clear();
				window.map.setNewMap(window.player.y, window.player.x);
				window.addInfo("You proceed down the stairs lower into the dungeon");
				
			}
			
			for (int i = 0; i < window.map.mobs.size(); i ++) {
				window.map.mobs.get(i).move(window.map.mapData, window.player.y, window.player.x);
				
				if (Math.abs(window.map.mobs.get(i).y - window.player.y) < 2 && Math.abs(window.map.mobs.get(i).x - window.player.x) < 2) {
					int roll = rand.nextInt(7) - 3;
					int damage = window.map.mobs.get(i).might - window.player.armour + roll;
					
					if (damage < 0)
						damage = 0;
					
					window.player.takeDamage(damage);
					window.addInfo("recieved " + damage + " physical damage : roll (" + (roll + 3) + ")");
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
