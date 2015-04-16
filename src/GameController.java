import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

//this class controls the main and window class
public class GameController {
	
	Main main;
	Window window;
	
	InputListener inputListener = new InputListener();
	BufferedImage gameScreen = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
	
	public GameController(Main main, Window window) {
		this.main = main;
		this.window = window;
		
		window.addKeyListener(inputListener);
		window.setGameScreen();
		
		gameLoop();
	}
	
	void gameLoop() {
		main.setUpGame();
		
		while(true) {
			window.setScreen(gameScreen);
		}
	}
	
	class InputListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
			switch(e.getKeyChar()) {
			case 'w': main.movePlayer(-10, 0);
				break;
			case 'a': main.movePlayer(0, -10);
				break;
			case 's': main.movePlayer(10, 0);
				break;
			case 'd': main.movePlayer(0, 10);
			}
			
			gameScreen.getGraphics().fillRect(main.getPlayerX(), main.getPlayerY(), 10, 10);
			
		}
		
	}
}
