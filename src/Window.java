
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.util.Random;

import javax.swing.*;

//this class controls what is displayed in the window
public class Window extends JFrame {
	
	Random rand = new Random();
	
	JPanel gameScreen = new JPanel();
	JButton start = new JButton("--Start--");

	Map map = new Map();
	Player player = new Player(10, 10, 10, 10);
	Mob[] mobs = new Mob[10];
	
	GameCanvas gameCanvas = new GameCanvas();
	
	boolean drawMap = true, drawMainMenu, drawCharSheet;
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		
	}
	
	void setTitleScreen() {
		gameScreen.removeAll();
		gameScreen.setLayout(new BorderLayout());
		
		gameScreen.add(start);
		
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
	
	//inner canvas class
	class GameCanvas extends Canvas {
		
		BufferedImage screen = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			Graphics bufferGraphics = screen.getGraphics();
			
			drawMap(bufferGraphics);
			drawUI(bufferGraphics);
			
			bufferGraphics.setColor(Color.green);
			bufferGraphics.fillRect(player.x, player.y, 10, 10);
			
			g.drawImage(screen, 0, 0, this);
		}
		
		void drawMobs(Graphics g) {
			
		}
		
		void drawUI(Graphics bufferGraphics) {
			
			bufferGraphics.setColor(Color.gray);
			bufferGraphics.fillRect(400, 0, 100, 400);
			bufferGraphics.fillRect(0, 400, 500, 100);
			
			bufferGraphics.setColor(Color.red);
			bufferGraphics.fillRect(400, 10, (player.health / player.fort) * 100, 20);
			bufferGraphics.setColor(Color.black);
			bufferGraphics.drawRect(400, 10, 100, 20);
			
			bufferGraphics.setColor(Color.black);
			bufferGraphics.drawString(player.getLevel(), 400, 50);
			
			bufferGraphics.drawRect(5, 405, 290, 60);
		}
		
		void drawMap(Graphics bufferGraphics) {
			
			for (int y = 0; y < map.mapData.length; y ++) {
				for (int x = 0; x < map.mapData.length; x ++) {
					
					bufferGraphics.setColor(Color.black);
					if (map.mapData[y][x] == 1)
						bufferGraphics.fillRect(x*10, y*10, 10, 10);
					
					bufferGraphics.setColor(Color.white);
					if (map.mapData[y][x] == 2)
						bufferGraphics.fillRect(x*10, y*10, 10, 10);
					
					bufferGraphics.setColor(Color.blue);
					if (map.mapData[y][x] == 3)
						bufferGraphics.fillRect(x*10, y*10, 10, 10);
				}
			}
		}
	}
}
