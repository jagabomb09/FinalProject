import java.awt.*;
import javax.swing.*;


public class Window extends JFrame{
	
	private JButton testButton = new JButton("Hello World");
	
	Window() {
		JPanel testPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(600,200);
		
		testPanel.add(testButton);
		
		this.add(testPanel);
	}
}
