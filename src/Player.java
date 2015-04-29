//user controlled player class
public class Player {
	
	int fort, might, intellect, will, armour, health, y, x;
	
	
	public Player(int fort, int might, int intellect, int will) {
		this.fort = fort;
		this.might = might;
		this.intellect = intellect;
		this.will = will;
		health = fort*10;
		
		x = 210;
		y = 210;
	}
	
	void takeDamage(int physical, int magical) {
		health -= physical - armour;
		health -= magical - will;
	}
}
