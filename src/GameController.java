import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

//this class controls game logic
public class GameController {
	
	Window window = new Window();
	InputListener inputListener = new InputListener();
	
	Random rand = new Random();
	
	boolean gameRunning = true;
	
	public GameController() {
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
			case "Teleport Scroll":
				int x = rand.nextInt(30) + 5;
				int y = rand.nextInt(30) + 5;
				
				while (window.map.mapData[y][x] != 2) {
					x = rand.nextInt(30) + 5;
					y = rand.nextInt(30) + 5;
				}
			
				window.player.y = y;
				window.player.x = x;
			}
			
			window.player.inventory[index] = null;
		}
		
		
	}
	
	void movePlayer(int yDir, int xDir) {
		try {
			if (window.map.mapData[window.player.y + yDir][window.player.x + xDir] != 1) {  //if location player is trying to move to is not a wall
				window.player.y += yDir;  //move to location
				window.player.x += xDir;
				
				for (int i = 0; i < window.map.mobs.size(); i ++) {  //loops through all mobs in the ArrayList
					if (window.player.y == window.map.mobs.get(i).y && window.player.x == window.map.mobs.get(i).x) {  //if player is in same location as mob
						
						int roll = rand.nextInt(7) - 3;  //roll (-3 to 3);
						window.map.mobs.get(i).takeDamage(window.player.getPhysicalDamage() + roll);  //damage mob with player might + roll
						window.addInfo("Attacked mob for " + (window.player.might - window.map.mobs.get(i).armour + roll) + " damage : roll (" + (roll + 3) + ")");  //add attack info to window
						
						if (window.map.mobs.get(i).health <= 0) {  //if mob health is less than 0
							
							if (window.player.xp + window.map.mobs.get(i).fort >= window.player.nextLevelXP)  //if player xp + xp from mob gets player to the next level
								window.addInfo("Level up!");
							
							window.player.gainXp(window.map.mobs.get(i).fort);  //add to player xp
							window.addInfo("+ " + window.map.mobs.get(i).fort + " xp");  //add xp info to window
							
							window.map.mobs.remove(i);  //remove mob from ArrayList
						}
							
						
						window.player.y -= yDir;  //move player back to original location
						window.player.x -= xDir;
					}
				}
				
				if (window.map.mapData[window.player.y][window.player.x] == 4) {  //if player moved to a item
					window.map.mapData[window.player.y][window.player.x] = 2;  //remove item from map
					int itemType = rand.nextInt(3);  //pick random itemType
					
					switch(itemType) {  //add item to player inventory
					case 0: window.player.addToInventory(new HealthPot());
					break;
					case 1: window.player.addToInventory(new MightPot());
					break;
					case 2: window.player.addToInventory(new TeleportScroll());
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException ob) {}
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
			if (gameRunning) {
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
				window.addInfo("You proceed down the stairs to level " + window.map.depth);
				
			}
			
			for (int i = 0; i < window.map.mobs.size(); i ++) {
				
				if (Math.abs(window.map.mobs.get(i).y - window.player.y) <= 1 && Math.abs(window.map.mobs.get(i).x - window.player.x) <= 1) {
					int roll = rand.nextInt(7) - 3;
					int damage = window.map.mobs.get(i).might - window.player.armor + roll;
					
					if (damage < 0)
						damage = 0;
					
					window.player.takeDamage(damage);
					
					if (window.player.health <= 0) {
						window.killPlayer();
						gameRunning = false;
					}
					
					window.addInfo("recieved " + damage + " physical damage : roll (" + (roll + 3) + ")");
				} else {
					window.map.mobs.get(i).move(window.map.mapData, window.player.y, window.player.x);
				}
			}
			
			window.updateScreen();
		}
		
	}
}
