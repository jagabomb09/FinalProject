import java.awt.event.*;

//this class controls game logic
public class GameController {
	
	Window window = new Window();
	InputListener inputListener = new InputListener();
	
	public GameController() {
		window.addKeyListener(inputListener);
		window.setGameScreen();
		
		window.setVisible(true);
		
		gameLoop();
	}
	
	void gameLoop() {
		while(true) {
			
		}
	}
	
	void movePlayer(int yDir, int xDir) {
		try {
			if (window.map.mapData[(window.player.y + yDir) / 10][(window.player.x + xDir) / 10] == 0) {
				window.player.y += yDir;
				window.player.x += xDir;
			}
		} catch (ArrayIndexOutOfBoundsException ob) {
			//when player tries to move outside the map
		}
		
		
		System.out.printf(":%d%n", window.player.x);
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
			case 'w': movePlayer(-10, 0);
				break;
			case 'a': movePlayer(0, -10);
				break;
			case 's': movePlayer(10, 0);
				break;
			case 'd': movePlayer(0, 10);
			}
			
			window.updateScreen();
		}
		
	}
}
