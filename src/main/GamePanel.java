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
	
	int FPS = 60;
	
	Thread gameThread;
	
	KeyHandler keyHandler = new KeyHandler();
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // increase rendering performance
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start(); // will call the run() method
	}
	
	// sleep method game loop
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// create game loop, the core of the game
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			update();
			repaint();
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				Thread.sleep((long) remainingTime);
				nextDrawTime = nextDrawTime + drawInterval;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	// delta or accumulator method game loop
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		// create game loop, the core of the game
//		double drawInterval = 1000000000/FPS;
//		double delta = 0;
//		long lastTime = System.nanoTime();
//		long currentTime;
//		
//		while(gameThread != null) {
//			currentTime = System.nanoTime();
//			delta = delta + (currentTime - lastTime) / drawInterval;
//			lastTime = currentTime;
//			
//			if (delta >= 1) {
//				update();
//				repaint();
//				delta = delta - 1;
//			}
//
//		}
//	}
	
	public void update() {
		if (keyHandler.upPressed == true) {
			playerY = playerY - playerSpeed;
		} else if (keyHandler.downPressed == true) {
			playerY = playerY + playerSpeed;
		} else if (keyHandler.rightPressed == true) {
			playerX = playerX + playerSpeed;
		} else if (keyHandler.leftPressed == true) {
			playerX = playerX - playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
		g2d.fillRect(playerX, playerY, tileSize, tileSize);
		g2d.dispose();
	}
}
