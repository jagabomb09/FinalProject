import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
	
	Main main;
	Window window;
	
	public GameController(Main main, Window window) {
		this.main = main;
		this.window = window;
		
		gameLoop();
	}
	
	void gameLoop() {
		
	}
	
	class testListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
}
