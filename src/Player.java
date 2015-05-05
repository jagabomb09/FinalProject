//user controlled player class
public class Player {
	
	int fort, might, intellect, will, armor, health, y, x, level, xp, nextLevelXP;
	Item[] inventory = new Item[9];
	
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
		inventory[0] = new HealthPot();
		
	}
	
	void levelUp() {
		xp = 0;
		level += 1;
		might += 2;
		armor += 2;
		
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
	
	String getArmor() {
		return ("Armor: " + armor);
	}
	
	void takeDamage(int physical) {
		health -= physical;
	}
}
