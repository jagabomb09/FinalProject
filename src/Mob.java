import java.util.ArrayList;
import java.util.Random;

public class Mob {
	Random rand = new Random();
	int y, x, health, might, intellect, will, armour, fort, yDir, xDir;
	
	Mob (int y, int x, int health, int might, int intellect, int will, int armour) {
		this.y = y;
		this.x = x;
		this.health = health;
		this.might = might;
		this.intellect = intellect;
		this.armour = armour;
		fort = health / 10;
	}
	
	void takeDamage(int physical) {
		if (physical - armour > 0)
			health -= (physical - armour);
	}
	
	void move(int[][] map, int playerY, int playerX, int playerStealth, ArrayList<Mob> mobs) {
		yDir = rand.nextInt(3) - 1;
		xDir = rand.nextInt(3) - 1;
		
		if (Math.abs(playerY - y) < playerStealth && Math.abs(playerX - x) < playerStealth) {
			if (playerY > y)
				yDir = 1;
			
			if (playerY < y)
				yDir = -1;
			
			if (playerX > x)
				xDir = 1;
			
			if (playerX < x)
				xDir = -1;
		}
		
		
		
		try {
			if (map[y + yDir][x] != 1 && y + yDir != playerY) {
				boolean open = true;
				
				for (int i = 0; i < mobs.size(); i ++) {
					if (mobs.get(i).y == (y + yDir))
						open = false;
				}
				
				if (open)
					y += yDir;
			}
				
			
			if (map[y][x + xDir] != 1 && x + xDir != playerX) {
				boolean open = true;
				
				for (int i = 0; i < mobs.size(); i ++) {
					if (mobs.get(i).x == (x + xDir))
						open = false;
				}
				
				if (open)
					x += xDir;
			}
				
			
		} catch (ArrayIndexOutOfBoundsException ob) {
			//when player tries to move outside the map
		}
		
		
	}
}
