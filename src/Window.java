
import java.awt.*;
import javax.swing.*;

//this class controls what is displayed in the window
public class Window extends JFrame {
	
	JPanel gameScreen = new JPanel();
	GameCanvas gameCanvas = new GameCanvas();
	Player player = new Player(10, 10, 10, 10);
	Map map = new Map();
	
	boolean drawMap = true, drawMainMenu, drawCharSheet;
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	}
	
	void setCharCreationScreen() {
		gameScreen.removeAll();
		
		//add elements to charCreation JPanel
		
		this.add(gameScreen);
	}
	
	void setGameScreen() {
		gameScreen.removeAll();
		gameScreen.setLayout(new BorderLayout());
		
		gameScreen.add(gameCanvas);
		
		this.add(gameScreen);
	}
	
	void updateScreen() {
		gameCanvas.repaint();
	}
	
	class GameCanvas extends Canvas {
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			if (drawMap) {
				drawMap(g);
				
				g.setColor(Color.red);
				g.fillRect(player.x, player.y, 10, 10);
			}
		}
		
		void drawMap(Graphics g) {
			for (int y = 0; y < map.mapData.length; y ++) {
				for (int x = 0; x < map.mapData.length; x ++) {
					
					g.setColor(Color.black);
					if (map.mapData[y][x] == 1)
						g.fillRect(x*10, y*10, 10, 10);
					
					g.setColor(Color.gray);
					if (map.mapData[y][x] == 0)
						g.fillRect(x*10, y*10, 10, 10);
				}
			}
		}
	}
}
