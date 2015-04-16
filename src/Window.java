import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

//this class controls what is displayed in the window
public class Window extends JFrame{
	
	JPanel gameScreen = new JPanel();
	
	BufferedImage screen = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
	GameCanvas gameCanvas = new GameCanvas();
	
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
	
	void setScreen(BufferedImage image) {
		screen = image;
		gameScreen.repaint();
	}
	
	class GameCanvas extends Canvas {
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			g.drawImage(screen, 0, 0, this);
		}
	}
}
