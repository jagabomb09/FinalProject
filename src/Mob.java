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
	
	void move(int[][] map,int playerY, int playerX) {
		yDir = rand.nextInt(3) - 1;
		xDir = rand.nextInt(3) - 1;
		
		if (Math.abs(playerY - y) < 7 && Math.abs(playerX - x) < 7) {
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
			if (map[y + yDir][x] != 1 && y + yDir != playerY)
				y += yDir;
			
			if (map[y][x + xDir] != 1 && x + xDir != playerX)
				x += xDir;
			
		} catch (ArrayIndexOutOfBoundsException ob) {
			//when player tries to move outside the map
		}
		
		
	}
}
