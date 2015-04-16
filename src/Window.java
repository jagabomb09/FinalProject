import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window extends JFrame{
	
	JPanel charCreation = new JPanel();
	JPanel game = new JPanel();
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(600,200);
	}
	
	void setCharCreationScreen() {
		this.getContentPane().removeAll();
		this.add(charCreation);
	}
	
	void setGameScreen() {
		this.getContentPane().removeAll();
		this.add(game);
	}
}
