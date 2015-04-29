
public class MobController {
	
	int[][] mobInfo = new int[7][10];
	
	MobController() {
		
	}
	
	void addMob(int x, int y, int health, int might, int intellect, int will, int armour) {
		for (int i = 0; i < mobInfo.length; i ++) {
			if (mobInfo[0][i] == 0) {
				mobInfo[0][i] = x;
				mobInfo[1][i] = y;
				mobInfo[2][i] = health;
				mobInfo[3][i] = might;
				mobInfo[4][i] = intellect;
				mobInfo[5][i] = will;
				mobInfo[6][i] = armour;
			}
		}
	}
	
	void killMob(int index) {
		for (int i = 0; i < 7; i ++) {
			mobInfo[i][index] = 0;
		}
	}
	
	
}
