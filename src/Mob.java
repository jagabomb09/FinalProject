import java.util.Random;

public class Mob {
	Random rand = new Random();
	int y, x, health, might, intellect, will, armour, fort;
	
	Mob (int y, int x, int health, int might, int intellect, int will, int armour) {
		this.y = y;
		this.x = x;
		this.health = health;
		this.might = might;
		this.intellect = intellect;
		this.armour = armour;
		fort = health / 10;
	}
	
	void takeDamage(int physical, int magical) {
		health -= (physical - armour);
		health -= (magical - will);
	}
	
	void move(int[][] map,int playerY,int playerX) {
		int yDir = rand.nextInt(3) - 1;
		int xDir = rand.nextInt(3) - 1;
		
		if (Math.abs(playerY - y) < 5 && Math.abs(playerX - x) < 5) {
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
			if (map[y + yDir][x + xDir] != 1 && y + yDir != playerY && x + xDir != playerX) {
				y += yDir;
				x += xDir;
			}
		} catch (ArrayIndexOutOfBoundsException ob) {
			//when player tries to move outside the map
		}
		
		
	}
}
