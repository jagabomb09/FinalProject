
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.util.Random;

import javax.swing.*;

//this class controls what is displayed in the window
public class Window extends JFrame {
	
	Random rand = new Random();
	JPanel gameScreen = new JPanel();
	Player player = new Player(10, 10, 10, 10);
	MobController mobController = new MobController();
	Map map = new Map();
	GameCanvas gameCanvas = new GameCanvas();
	
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
		
		BufferedImage mapImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		
		GameCanvas() {
			createMap();
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			if (drawMap)
				g.drawImage(mapImage, 0, 0, this);
				
			g.setColor(Color.green);
			g.fillRect(player.x, player.y, 10, 10);
			
			drawUI(g);
		}
		
		void drawMobs(Graphics g) {
			g.setColor(Color.red);
			
			for (int i = 0; i < mobController.mobInfo.length; i ++) {
				if (mobController.mobInfo[0][i] != 0) {
					g.fillRect(mobController.mobInfo[0][i], mobController.mobInfo[1][i], 10, 10);
				}
			}
		}
		
		void drawUI(Graphics g) {
			g.setColor(Color.gray);
			g.fillRect(400, 0, 100, 400);
			g.fillRect(0, 400, 500, 100);
			
			g.setColor(Color.red);
			g.fillRect(400, 10, (player.health / player.fort) * 100, 20);
			g.setColor(Color.black);
			g.drawRect(400, 10, 100, 20);
		}
		
		void createMap() {
			Graphics g = mapImage.getGraphics();
			
			for (int y = 0; y < map.mapData.length; y ++) {
				for (int x = 0; x < map.mapData.length; x ++) {
					
					g.setColor(Color.black);
					if (map.mapData[y][x] == 1)
						g.fillRect(x*10, y*10, 10, 10);
					
					g.setColor(Color.white);
					if (map.mapData[y][x] == 2)
						g.fillRect(x*10, y*10, 10, 10);
				}
			}
		}
	}
}
