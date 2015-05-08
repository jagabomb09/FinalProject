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
		
		yLoc = yStart;  //go to starting location
		xLoc = xStart;
		
		smartMapCreation();  //create map
		
		while(testMap(.25) == false) {  //while map is less that 25% open space
			clearMap();
			
			yLoc = yStart;  //reset to starting location
			xLoc = xStart;
			
			smartMapCreation();  //create map
		}
		
		addMobs(2 + (int) (depth * .6));  //add mobs to map
		
		addItems((int) (depth * .5));  //add items to map
		
		mapData[yLoc][xLoc] = 3;  //add door at finishing location

		for (int y = 0; y < mapData.length; y ++) {  //fill all open space will walls
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
			
			mobs.add(new Mob(mobY, mobX, 100, 1 + (int) (depth * .5), 0, 1, 1 + (int) (depth * .9)));
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
		roomSize = rand.nextInt(5) + 5;  //pick a random room starting room size between 5 and 9
		
		for (int y = yLoc; y < yLoc + roomSize; y ++) {  //fill an area using room size
			for (int x = xLoc; x < xLoc + roomSize; x ++) {
				mapData[y][x] = 2;
			}
		}
		
		xLoc += rand.nextInt(roomSize);  //pick a random x and y location inside the room
		yLoc += rand.nextInt(roomSize);
		
		direction = rand.nextInt(4) + 1;  //pick a random direction to build the next room
		
		while (mapData[yLoc][xLoc] == 2) {  //while current location is inside the start room keep moving using direction
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
		
		try {  //this is to catch ArrayOutOfBounds errors being thrown
			while(true) {
				direction = rand.nextInt(4) + 1; //pick a random direction
				roomType = rand.nextInt(5);  //pick a random room type
				
				if (roomType < 4) {  //this will create a box shaped room with random width and length
					roomLength = rand.nextInt(5) + 2;
					roomWidth = rand.nextInt(5);
				}
				
				if (roomType == 4) {  //this will create a hallway with random length and a width of 1
					roomLength = rand.nextInt(5) + 6;
					roomWidth = 0;
				}
				
				int tries = 0;  //set counter tries to 0
				while(scanArea(yLoc, xLoc, direction, 0, roomLength) == false) {  //keep scanning the area of the next room until you find a open area
					direction = rand.nextInt(4) + 1;  //pick a random direction
					roomType = rand.nextInt(5);  //pick a random room type
					
					if (roomType < 4) {  //this will create a box shaped room with random width and length
						roomLength = rand.nextInt(5) + 2;
						roomWidth = rand.nextInt(5);
					}
					
					if (roomType == 4) {  //this will create a hallway with random length and a width of 1
						roomLength = rand.nextInt(5) + 6;
						roomWidth = 0;
					}
					
					while (mapData[yLoc][xLoc] == 2) {  //while current location is inside a room keep moving in current direction
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
					
					tries += 1;  //add to tries counter
					
					if (tries == 15)  //if tries is equal to 15 stop building the level
						return;
				}
				
				fillArea(yLoc, xLoc, direction, roomWidth, roomLength, 2);  //fill area that has been scanned and found to be open
			}
		} catch (ArrayIndexOutOfBoundsException ob) {
			//this is empty because the program should just continue if it any location is out of bounds
		}
		
	}
	
	void fillArea(int y, int x, int direction, int width, int length, int type) {
		for (int i = roomLength; i > 0; i --) {  //loops (length) amount of times, moving using direction each loop
			if (direction == 1 || direction == 3) {  //if direction is north or south
				int xOfset = x - roomWidth;  //set xOfset
				
				while(xOfset < x + roomWidth) {  //until xOfset is equal to x + width keep moving right
					if (mapData[y][xOfset] != 1)  //if there is not a wall at the current location
						mapData[y][xOfset] = type;  //fill current location with (type)
					
					xOfset += 1;
				}
			}
			
			if (direction == 2 || direction == 4) { //if direction is east or west
				int yOfset = y - roomWidth;  //set yOfset
				
				while(yOfset < y + roomWidth) {
					if (mapData[yOfset][x] != 1)
						mapData[yOfset][x] = type;
					
					yOfset += 1;
				}
			}
			
			mapData[y][x] = type;
			
			switch (direction) { //move using direction
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
		for (int i = length + 1; i > 0; i --) {  //loop (length) amount of times
			
			if (y > mapData.length - 1 || y < 0 || x > mapData.length - 1 || x < 0) //if current location is outside the bounds of the map
				return false;
			
			switch (direction) {
			case 1: if (mapData[y][x + width] == 2 || mapData[y][x - width] == 2)  //check left and right bounds of the room
				return false;
			break;
			case 2: if (mapData[y + width][x] == 2 || mapData[y - width][x] == 2)  //check top and bottom bounds of the room
				return false;
			break;
			case 3: if (mapData[y][x + width] == 2 || mapData[y][x - width] == 2)  //check left and right bounds of the room
				return false;
			break;
			case 4: if (mapData[y + width][x] == 2 || mapData[y - width][x] == 2)  //check top and bottom bounds of the room
				return false;
			}
			
			switch (direction) {  //move using direction
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
