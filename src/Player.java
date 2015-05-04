//user controlled player class
public class Player {
	
	int fort, might, intellect, will, armour, health, y, x, level, xp, nextLevelXP;
	Item[] inventory = new Item[9];
	Item test = new MightPot();
	
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
		
		nextLevelXP = level * 12;
		for (int i = 0; i < inventory.length; i ++) {
			inventory[i] = test;
		}
		
	}
	
	void levelUp() {
		xp = 0;
		level += 1;
		might += 2;
		armour += 2;
		
		nextLevelXP = level * 12;
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
	
	String getArmour() {
		return ("Armour: " + armour);
	}
	
	void takeDamage(int physical) {
		if (physical - armour > 0)
			health -= physical - armour;
	}
}
