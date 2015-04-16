
public class Player {
	
	int fort, might, intellect, will, armour, health, y, x;
	
	
	public Player(int fort, int might, int intellect, int will) {
		this.might = might;
		this.intellect = intellect;
		this.will = will;
		health = fort*10;
		
		y = 10;
		x = 10;
	}
}
