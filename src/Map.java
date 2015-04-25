import java.awt.image.BufferedImage;
import java.util.Random;

//map information
public class Map {
	
	int[][] mapData = new int[20][20];
	int y, x, count, roomSize;
	
	Random rand = new Random();
	
	public Map() {
		testGenerateMap(4);
	}
	
	void setRandomMap() {
		for (y = 0; y < mapData.length; y ++) {
			for (x = 0; x < mapData.length; x ++) {
				mapData[y][x] = rand.nextInt(2);
			}
		}
	}
	
	void testGenerateMap(int rooms) {
		count = 0;
		
		while (count <= rooms) {
			count += 1;
			
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			roomSize = rand.nextInt(7);
			
			for (int yTemp = y; yTemp < y + roomSize; y ++) {
				for (int xTemp = x; xTemp < x + roomSize; x ++) {
					if (xTemp < 20 && yTemp < 20) {
						mapData[xTemp][yTemp] = 1;
					}
				}
			}
		}
	}
}
