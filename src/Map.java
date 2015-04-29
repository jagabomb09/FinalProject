import java.awt.image.BufferedImage;
import java.util.Random;

//map information
//1 = wall, 2 = floor
//1-N, 2-E, 3-S, 4-W
public class Map {
	
	int[][] mapData = new int[40][40];
	int yStart, xStart, xLoc, yLoc, count, roomSize, direction, roomType, roomWidth, roomLength;
	
	Random rand = new Random();
	
	Map() {
		setNewMap();
	}
	
	void setNewMap() {
		for (int y = 0; y < mapData.length; y ++) {
			for (int x = 0; x < mapData.length; x ++) {
				mapData[y][x] = 0;
			}
		}
		
		smartMapCreation();

		for (int y = 0; y < mapData.length; y ++) {
			for (int x = 0; x < mapData.length; x ++) {
				
				if (mapData[y][x] == 0)
					mapData[y][x] = 1;
			}
		}
		
		mapData[yLoc][xLoc] = 0;
	}
	
	void smartMapCreation() {
		xLoc = 20;
		yLoc = 20;
		roomSize = rand.nextInt(5) + 5;
		
		for (int y = yLoc; y < yLoc + roomSize; y ++) {
			for (int x = xLoc; x < xLoc + roomSize; x ++) {
				mapData[y][x] = 2;
			}
		}
		
		xLoc += rand.nextInt(roomSize);
		yLoc += rand.nextInt(roomSize);
		
		direction = rand.nextInt(4) + 1;
		
		while (mapData[yLoc][xLoc] == 2) {
			switch (direction) {
			case 1: yLoc -= 1;
			break;
			case 2: xLoc += 1;
			break;
			case 3: yLoc += 1;
			break;
			case 4: xLoc -= 1;
			}
		}
		
		while(true) {
			direction = rand.nextInt(4) + 1;
			
			
			roomType = rand.nextInt(5);
			
			if (roomType < 4) {
				roomLength = rand.nextInt(9);
				roomWidth = rand.nextInt(5) + 2;
			}
			
			if (roomType == 4) {
				roomLength = rand.nextInt(5) + 6;
				roomWidth = 0;
			}
			
			int tries = 0;
			while(scanArea(yLoc, xLoc, direction, 0, roomLength) == false) {
				direction += 1;
				
				roomType = rand.nextInt(5);
				
				if (roomType < 4) {
					roomLength = rand.nextInt(9);
					roomWidth = rand.nextInt(5);
				}
				
				if (roomType == 4) {
					roomLength = rand.nextInt(5) + 6;
					roomWidth = 0;
				}
				
				if (direction == 5)
					direction = 1;
				
				while (mapData[yLoc][xLoc] == 2) {
					switch (direction) {
					case 1: yLoc -= 1;
					break;
					case 2: xLoc += 1;
					break;
					case 3: yLoc += 1;
					break;
					case 4: xLoc -= 1;
					}
				}
				
				tries += 1;
				
				if (tries == 6)
					return;
			}
			
			if (scanArea(yLoc, xLoc, direction, 0, roomLength)) {
				for (int i = roomLength; i > 0; i --) {
					try {
						if (direction == 1 || direction == 3) {
							int x = xLoc - roomWidth;
							
							while(x < xLoc + roomWidth) {
								if (mapData[yLoc][x] != 1)
									mapData[yLoc][x] = 2;
								
								x += 1;
							}
						}
						
						if (direction == 2 || direction == 4) {
							int y = yLoc - roomWidth;
							
							while(y < yLoc + roomWidth) {
								if (mapData[y][xLoc] != 1)
									mapData[y][xLoc] = 2;
								
								y += 1;
							}
						}
					} catch (ArrayIndexOutOfBoundsException ob) {}
					
					mapData[yLoc][xLoc] = 2;
					
					switch (direction) {
					case 1: yLoc -= 1;
					break;
					case 2: xLoc += 1;
					break;
					case 3: yLoc += 1;
					break;
					case 4: xLoc -= 1;
					}
				}
			}
		}
	}
	
	boolean scanArea(int y, int x, int direction, int width, int length) {
		for (int i = length + 1; i > 0; i --) {
			
			if (y > mapData.length - 1 || y < 0 || x > mapData.length - 1 || x < 0)
				return false;
			
			switch (direction) {
			case 1: if (mapData[y][x + width] == 2 || mapData[y][x - width] == 2)
				return false;
			break;
			case 2: if (mapData[y + width][x] == 2 || mapData[y - width][x] == 2)
				return false;
			break;
			case 3: if (mapData[y][x + width] == 2 || mapData[y][x - width] == 2)
				return false;
			break;
			case 4: if (mapData[y + width][x] == 2 || mapData[y - width][x] == 2)
				return false;
			}
			
			switch (direction) {
			case 1: y -= 1;
			break;
			case 2: x += 1;
			break;
			case 3: y += 1;
			break;
			case 4: x -= 1;
			}
		}
		
		return true;
	}
}
