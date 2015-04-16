
public class FinalProject {

	public static void main(String[] args) {
		Main main = new Main();
		Window window = new Window();
		
		window.setVisible(true);
		
		GameController gameController = new GameController(main, window);
	}

}
