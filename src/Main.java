
//this class contains all the game logic
public class Main {
	
	Player player;
	
	int[][] map = new int[10][10];

	public Main() { 
		
	}
	
	void setUpGame() {
		player = new Player(10, 10, 10, 10);
	}
	
	int getPlayerX() {
		return player.x;
	}
	
	int getPlayerY() {
		return player.y;
	}
	
	void movePlayer(int yDir, int xDir) {
		player.y += yDir;
		player.x += xDir;
		
		System.out.println("Moving");
	}
}
