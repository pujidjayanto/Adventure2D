package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;


import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	/*
	 * 16 * 16 pixel, standard size
	 * The looks will be differ in each screen resolution
	 */
	final int originalTileSize = 16;
	
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; // 48 * 48 size
	
	final int maxScreenColumn = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenColumn; // 48 * 16 = 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 48 * 12 = 576 pixels
	
	Thread gameThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // increase rendering performance
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start(); // will call the run() method
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// create game loop, the core of the game
		while(gameThread != null) {
			System.out.println("The game loop is running");
			update();
			repaint();
		}
	}
	
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
		g2d.fillRect(100, 100, tileSize, tileSize);
		g2d.dispose();
	}
}
