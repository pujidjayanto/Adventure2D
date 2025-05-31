package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tiles;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[10];
		mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int row = 0;
			int col = 0;
			while(col < gp.maxScreenColumn && row < gp.maxScreenRow) {
				String line = br.readLine();
				while(col < gp.maxScreenColumn) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxScreenColumn) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void draw(Graphics2D g2d) {
		// g2d.drawImage(tiles[0].image, 0,0, gp.tileSize, gp.tileSize, null);
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenColumn && row < gp.maxScreenRow) {
			int tileNum = mapTileNum[col][row];
			g2d.drawImage(tiles[tileNum].image, x,y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			if (col == gp.maxScreenColumn) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
}
