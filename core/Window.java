package core;
import java.awt.Dimension;

import javax.swing.JFrame;

import input.key.KeyInput;
import input.mouse.MouseInput;

public class Window {
    private KeyInput keyListener;
    private MouseInput mouseListener;

	private int width,height;
	
	public Window(int width, int height, String title, Main main) {
		this.width=width;
		this.height=height;
        keyListener = new KeyInput();
        mouseListener = new MouseInput(main);
		
		JFrame frame = new JFrame(title);     // Creating new frame with the title in the parameters when this class is called
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.add(main);     // Adding the main class to the actual window
        frame.addKeyListener(keyListener);
        frame.addMouseListener(mouseListener);
		frame.setResizable(false);     // Cannot resize window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Allows the x button to work
		frame.setLocationRelativeTo(null);     // Box will start in the center
		frame.setVisible(true);     // Showing the window
		
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

}
