package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public Player(GamePanel gp, KeyHandler kh) {
		this.gamePanel = gp;
		this.keyHandler = kh;
		setDefaultValues();
		getPlayerImages();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImages() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyHandler.upPressed == true || 
				keyHandler.downPressed == true ||
				keyHandler.leftPressed == true ||
				keyHandler.rightPressed == true
			) {
			if (keyHandler.upPressed == true) {
				direction = "up";
				y = y - speed;
			} else if (keyHandler.downPressed == true) {
				direction = "down";
				y = y + speed;
			} else if (keyHandler.rightPressed == true) {
				direction = "right";
				x = x + speed;
			} else if (keyHandler.leftPressed == true) {
				direction = "left";
				x = x - speed;
			}
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNumber == 1) {
					spriteNumber = 2;
				} else if (spriteNumber == 2) {
					spriteNumber = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2d) {
//		g2d.setColor(Color.white);
//		g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
		
		BufferedImage image = null;
		switch (direction) {
		case "up": 
			if (spriteNumber == 1) {
				image = up1;
			}
			if (spriteNumber == 2) {
				image = up2;
			}
			break;
		case "down": 
			if (spriteNumber == 1) {
				image = down1;
			}
			if (spriteNumber == 2) {
				image = down2;
			}
			break;
		case "left": 
			if (spriteNumber == 1) {
				image = left1;
			}
			if (spriteNumber == 2) {
				image = left2;
			}
			break;
		case "right": 
			if (spriteNumber == 1) {
				image = right1;
			}
			if (spriteNumber == 2) {
				image = right2;
			}
			break;
		}
		
		g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
	}
}
