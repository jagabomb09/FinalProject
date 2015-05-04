//user controlled player class
public class Player {
	
	int fort, might, intellect, will, armour, health, y, x, level, xp, nextLevelXP;
	
	public Player(int fort, int might, int intellect, int will) {
		this.fort = fort;
		this.might = might;
		this.intellect = intellect;
		this.will = will;
		health = fort*10;
		
		x = 11;
		y = 11;
		level = 1;
		xp = 0;
		
		nextLevelXP = level * 12;
	}
	
	void levelUp() {
		xp = 0;
		level += 1;
		
		nextLevelXP = level * 12;
	}
	
	int getPhysicalDamage() {
		return might;
	}
	
	String getLevel() {
		return ("level: " + level);
	}
	
	void gainXp(int amount) {
		xp += amount;
		
		if (xp >= nextLevelXP)
			levelUp();
	}
	
	String getXp() {
		return ("XP:" + xp + "(" + nextLevelXP + ")");
	}
	
	String getMight() {
		return ("Might: " + might);
	}
	
	String getArmour() {
		return ("Armour: " + armour);
	}
	
	void takeDamage(int physical, int magical) {
		health -= physical - armour;
		health -= magical - armour;
	}
}
