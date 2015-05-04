import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

//map information
//1 = wall, 2 = floor
//1-N, 2-E, 3-S, 4-W
public class Map {
	
	int[][] mapData = new int[40][40];
	ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	int yStart, xStart, xLoc, yLoc, count, roomSize, direction, roomType, roomWidth, roomLength, depth = 0;
	
	Random rand = new Random();
	
	Map() {
		setNewMap(10, 10);
	}
	
	void setNewMap(int yStart, int xStart) {
		depth += 1;
		
		clearMap();
		
		yLoc = yStart;
		xLoc = xStart;
		
		smartMapCreation();
		
		while(testMap(.25) == false) {
			clearMap();
			
			yLoc = yStart;
			xLoc = xStart;
			
			smartMapCreation();
		}
		
		addMobs(2 + (int) (depth * .6));
		
		if (depth > 2)
			addItems(1 + rand.nextInt((int) (depth * .5)));
		
		mapData[yLoc][xLoc] = 3;

		for (int y = 0; y < mapData.length; y ++) {
			for (int x = 0; x < mapData.length; x ++) {
				
				if (mapData[y][x] == 0)
					mapData[y][x] = 1;
			}
		}
		
		//mapData[yLoc][xLoc] = 0;
	}
	
	void clearMap() {
		for (int y = 0; y < mapData.length; y ++) {
			for (int x = 0; x < mapData.length; x ++) {
				mapData[y][x] = 0;
			}
		}
	}
	
	boolean testMap(double percentage) {
		int openSpace = 0;
		
		for (int y = 0; y < mapData.length; y ++) {
			for (int x = 0; x < mapData.length; x ++) {
				
				if (mapData[y][x] == 2)
					openSpace += 1;
			}
		}
		
		if (openSpace >= (mapData.length * mapData.length) * percentage)
			return true;
		
		return false;
	}
	
	void addMobs(int amount) {
		int mobY, mobX;
		
		for (int i = 0; i < amount; i ++) {
			mobY = rand.nextInt(39);
			mobX = rand.nextInt(39);
			
			while(mapData[mobY][mobX] != 2) {
				mobY = rand.nextInt(39);
				mobX = rand.nextInt(39);
			}
			
			mobs.add(new Mob(mobY, mobX, 100 + depth * 3, 1 + (int) (depth * .4), 0, 1, 1 + (int) (depth * .2)));
		}
	}
	
	void addItems(int amount) {
		int itemY, itemX;
		
		for (int i = 0; i < amount; i ++) {
			itemY = rand.nextInt(39);
			itemX = rand.nextInt(39);
			
			while(mapData[itemY][itemX] != 2) {
				itemY = rand.nextInt(39);
				itemX = rand.nextInt(39);
			}
			
			mapData[itemY][itemX] = 4;
		}
	}
	
	void smartMapCreation() {
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
		
		try {
			while(true) {
				direction = rand.nextInt(4) + 1;
				
				
				roomType = rand.nextInt(5);
				
				if (roomType < 4) {
					roomLength = rand.nextInt(5) + 2;
					roomWidth = rand.nextInt(5);
				}
				
				if (roomType == 4) {
					roomLength = rand.nextInt(5) + 6;
					roomWidth = 0;
				}
				
				int tries = 0;
				while(scanArea(yLoc, xLoc, direction, 0, roomLength) == false) { //I found that setting width of the area scanned to 0 made for more interesting maps
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
					
					tries += 1;
					
					if (tries == 15)
						return;
				}
				
				fillArea(yLoc, xLoc, direction, roomWidth, roomLength, 2);
			}
		} catch (ArrayIndexOutOfBoundsException ob) {
			//this is empty because the program should just continue if it any location is out of bounds
		}
		
	}
	
	void fillArea(int y, int x, int direction, int width, int length, int type) {
		for (int i = roomLength; i > 0; i --) {
			if (direction == 1 || direction == 3) {
				int xOfset = x - roomWidth;
				
				while(xOfset < x + roomWidth) {
					if (mapData[y][xOfset] != 1)
						mapData[y][xOfset] = type;
					
					xOfset += 1;
				}
			}
			
			if (direction == 2 || direction == 4) {
				int yOfset = y - roomWidth;
				
				while(yOfset < y + roomWidth) {
					if (mapData[yOfset][x] != 1)
						mapData[yOfset][x] = type;
					
					yOfset += 1;
				}
			}
			
			mapData[y][x] = 2;
			
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
		
		yLoc = y; //set xLoc and yLoc to end of created room
		xLoc = x;
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
