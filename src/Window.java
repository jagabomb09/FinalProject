
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

//this class controls what is displayed in the window
public class Window extends JFrame {
	
	Random rand = new Random();
	
	JPanel gameScreen = new JPanel();

	Map map = new Map();
	Player player = new Player(10, 20, 9, 8);
	
	GameCanvas gameCanvas = new GameCanvas();
	
	ArrayList<String> gameInfo = new ArrayList<String>();
	
	boolean drawGame = true, drawKillScreen = false;
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		gameScreen.setLayout(new BorderLayout());
	}
	
	void killPlayer() {
		drawKillScreen = true;
	}
	
	void setGameScreen() {
		gameScreen.removeAll();
		
		gameScreen.add(gameCanvas);
		
		this.add(gameScreen);
	}
	
	void updateScreen() {
		gameCanvas.repaint();
	}
	
	void addInfo(String message) {
		gameInfo.add(0, message);
		
		if (gameInfo.size() > 5) {
			gameInfo.remove(gameInfo.size() - 1);
		}
	}
	
	//inner canvas class
	class GameCanvas extends Canvas {
		
		BufferedImage screen = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			Graphics bufferGraphics = screen.getGraphics();
			
			if (drawGame) {
				drawMap(bufferGraphics);
				drawUI(bufferGraphics);
				drawMobs(bufferGraphics);
				
				bufferGraphics.setColor(Color.green);
				bufferGraphics.fillRect(player.x * 10, player.y * 10, 10, 10);
			}
			
			if (drawKillScreen) {
				bufferGraphics.setColor(Color.white);
				bufferGraphics.fillRect(0, 0, 500, 500);
				
				bufferGraphics.setColor(Color.black);
				bufferGraphics.drawString("You have died", 230, 230);
			}
			
			g.drawImage(screen, 0, 0, this);
		}
		
		void drawMobs(Graphics bufferGraphics) {
			for (int i = 0; i < map.mobs.size(); i ++) {
				bufferGraphics.setColor(Color.black);
				bufferGraphics.fillRect(map.mobs.get(i).x * 10, map.mobs.get(i).y * 10, 10, 10);
				
				bufferGraphics.setColor(Color.red);
				bufferGraphics.fillRect(map.mobs.get(i).x * 10, map.mobs.get(i).y * 10, 10, map.mobs.get(i).health / 10);
			}
		}
		
		void drawUI(Graphics bufferGraphics) {
			
			bufferGraphics.setColor(Color.gray);
			bufferGraphics.fillRect(400, 0, 100, 400);
			bufferGraphics.fillRect(0, 400, 500, 100);
			
			bufferGraphics.setColor(Color.green);
			bufferGraphics.fillRect(400, 10, player.health, 20);
			bufferGraphics.setColor(Color.black);
			bufferGraphics.drawRect(400, 10, 100, 20);
			bufferGraphics.drawString(" " + player.health, 405, 25);
			
			bufferGraphics.setColor(Color.black);
			bufferGraphics.drawString(player.getLevel(), 400, 50);
			bufferGraphics.drawString(player.getXp(), 400, 70);
			bufferGraphics.drawString(player.getMight(), 400, 90);
			bufferGraphics.drawString(player.getArmor(), 400, 110);
			
			for (int i = 0; i < player.inventory.length; i ++) {
				if (player.inventory[i] != null)
					bufferGraphics.drawString((i + 1) + ": " + player.inventory[i].getName(), 400, 150 + (i * 20));
			}
			
			bufferGraphics.drawRect(5, 405, 290, 60);
			
			for (int i = 0; i < gameInfo.size(); i ++) {
				bufferGraphics.drawString((String) gameInfo.get(i), 10, 420 + (i*10));
			}
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
					
					bufferGraphics.setColor(new Color(185, 122, 87));
					if (map.mapData[y][x] == 3)
						bufferGraphics.fillRect(x*10, y*10, 10, 10);
					
					bufferGraphics.setColor(Color.pink);
					if (map.mapData[y][x] == 4)
						bufferGraphics.fillRect(x*10, y*10, 10, 10);
				}
			}
		}
	}
}
