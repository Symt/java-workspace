package pong;

import java.awt.*;

import javax.swing.*;

public class Window {

	public Window(Main main) {
		JFrame frame = new JFrame("Pong");
		
		
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setMaximumSize(new Dimension(800, 600));
		frame.setMinimumSize(new Dimension(800, 600));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(main);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		main.start();
		
	}
	
}
