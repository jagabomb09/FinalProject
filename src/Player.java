
public class Player {
	
	int fort, might, intellect, will, armour, health;
	
	public Player(int fort, int might, int intellect, int will, int armour) {
		this.might = might;
		this.intellect = intellect;
		this.will = will;
		this.armour = armour;
		health = fort*10;
	}
}
