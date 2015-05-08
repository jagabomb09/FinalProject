//user controlled player class
public class Player {
	
	int fort, might, intellect, will, armor, health, y, x, level, xp, nextLevelXP, stealth;
	Item[] inventory = new Item[9];
	int[][] sightMap = new int[40][40];
	
	public Player(int fort, int might, int intellect, int will) {
		this.fort = fort;
		this.might = might;
		this.intellect = intellect;
		this.will = will;
		health = fort * 10;
		
		x = 11;
		y = 11;
		level = 1;
		xp = 0;
		stealth = 7;
		
		nextLevelXP = level * 13;
		inventory[0] = new StealthScroll();
		
	}
	
	void updateSightMap() {
		for (int yA = 0; yA < sightMap.length; yA ++) {
			for (int xA = 0; xA < sightMap.length; xA ++) {
				if (Math.sqrt(Math.pow(xA - x, 2) + Math.pow(yA - y, 2)) < 8)
					sightMap[yA][xA] = 1;
			}
		}
	}
	
	void clearSightMap() {
		for (int yA = 0; yA < sightMap.length; yA ++) {
			for (int xA = 0; xA < sightMap.length; xA ++) {
				sightMap[yA][xA] = 0;
			}
		}
	}
	
	void levelUp() {
		xp = 0;
		level += 1;
		might += 2;
		armor += 1;
		
		nextLevelXP = level * 13;
	}
	
	void addToInventory(Item item) {
		for (int i = 0; i < inventory.length; i ++) {
			
			if (inventory[i] == null) {
				inventory[i] = item;
				return;
			}
				
		}
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
	
	String getArmor() {
		return ("Armor: " + armor);
	}
	
	String getStealth() {
		return ("Stealth: " + stealth);
	}
	
	void takeDamage(int physical) {
		health -= physical;
	}
}
