package core;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import input.Camera;
import input.key.KeyInput;
import input.mouse.MouseInput;

public class Main extends Canvas implements Runnable {
	
	private static Main main; // Hold a static copy of main for bad programming
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private Window window;
	private MainContainer container;

	public static final int HEIGHT= 700, WIDTH=800;

	public Main() {
	}

	public void setup(){
		handler = new Handler();
		camera = new Camera(0, 0);
		container = new MainContainer();
		window = new Window(WIDTH, HEIGHT, "Test Window", this);
		start();
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {     // This is the game loop created by Notch, the creator of Minecraft
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {     // Update all the things in the game
		handler.tick();
		camera.tick();
	}
	
	public void render() {     // Render all the graphics
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////////////////////////////
		
		g.setColor(Color.black);
		g.fillRect(0, 0, window.getWidth(), window.getHeight());

		g2d.translate(-camera.getX(), -camera.getY());     // Everything in-between this...
		
		/*
		for(int xx = 0; xx < 30*72; xx+=32) {
			for(int yy = 0; yy < 30*72; yy+=32) {
				g.drawImage(floor, xx, yy, null);
			}
		}
		*/
		
		handler.render(g);
		
		g2d.translate(camera.getX(), camera.getY());     // ...and this is being translated
		
		
		/*
		g.setColor(Color.gray);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.green);
		g.fillRect(5, 5, hp*2, 32);
		g.setColor(Color.black);
		g.drawRect(5, 5, 200, 32);
		
		g.setColor(Color.white);
		g.drawString("Ammo: " + ammo, 5, 50);
		*/
		
		
		//////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	
	public static void main(String args[]) {
		main = new Main();
		main.setup();
	}

	public static Main get(){
		return main;
	}

	public Handler getHandler(){
		return handler;
	}

	public Camera getCamera(){
		return camera;
	}

	public Window getWindow(){
		return window;
	}

	public static int randomInt(int minInclusive, int maxInclusive){
        int range = maxInclusive - minInclusive + 1;
        int rng = (int)(Math.random() * range) + minInclusive;
        return rng;
    }

    public static double randomDouble(double minInclusive, double maxInclusive){
        double range = maxInclusive - minInclusive + 1;
        double rng = (Math.random() * range) + minInclusive;
        return rng;
    }

	public static long randomLong(long minInclusive, long maxInclusive){
		long range = maxInclusive - minInclusive + 1;
		long rng = (long)(Math.random() * range) + minInclusive;
		return rng;
	}

	

}
