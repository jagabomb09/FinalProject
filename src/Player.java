//user controlled player class
public class Player {
	
	int fort, might, intellect, will, armour, health, y, x, level, xp, nextLevelXP;
	
	
	public Player(int fort, int might, int intellect, int will) {
		this.fort = fort;
		this.might = might;
		this.intellect = intellect;
		this.will = will;
		health = fort*10;
		
		x = 110;
		y = 110;
		level = 0;
		xp = 0;
	}
	
	void levelUp() {
		xp = 0;
		
		nextLevelXP = level * 12;
	}
	
	String getLevel() {
		return ("level: " + level);
	}
	
	void getXP(int amount) {
		xp += amount;
	}
	
	void takeDamage(int physical, int magical) {
		health -= physical - armour;
		health -= magical - will;
	}
}
