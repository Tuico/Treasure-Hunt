package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import main.UtilityTool;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[100];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/Dungeon.txt");
	}
	
	public void getTileImage() {
		
		
//		setup(0, "0_00", true);
		setup(1, "0_00", true);
		setup(2, "0_01", true);
		setup(3, "0_02", true);
		setup(4, "0_03", true);
		setup(5, "0_04", true);
		setup(6, "0_05", true);
		setup(7, "0_06", false);
		setup(8, "0_07", false);
		setup(9, "0_08", false);
		setup(10, "0_09", false);
		setup(11, "0_10", true);
		setup(12, "0_11", false);
		setup(13, "0_12", false);
		setup(14, "0_13", false);
		setup(15, "0_14", false);
		setup(16, "0_15", true);
		setup(17, "0_16", false);
		setup(18, "0_17", false);
		setup(19, "0_18", false);
		setup(20, "0_19", false);
		setup(21, "0_20", true);
		setup(22, "0_21", false);
		setup(23, "0_22", false);
		setup(24, "0_23", false);
		setup(25, "0_24", false);
		setup(26, "0_25", true);
		setup(27, "0_26", false);
		setup(28, "0_27", false);
		setup(29, "0_28", false);
		setup(30, "0_29", false);
		setup(31, "0_30", true);
		setup(32, "0_31", false);
		setup(33, "0_32", false);
		setup(34, "0_33", false);
		setup(35, "0_34", false);
		setup(36, "0_35", true);
//		setup(37, "0_00", false);
//		setup(38, "0_00", false);
//		setup(39, "0_00", false);
//		setup(40, "0_00", true);
		setup(41, "0_40", true);
		setup(42, "0_41", true);
		setup(43, "0_42", true);
		setup(44, "0_43", true);
		setup(45, "0_44", true);
		setup(46, "0_45", true);
//		setup(47, "0_00", true);
//		setup(48, "0_00", true);
//		setup(49, "0_00", true);
		setup(50, "0_49", true);
		setup(51, "0_50", true);
		setup(52, "0_51", true);
		setup(53, "0_52", true);
		setup(54, "0_53", true);
		setup(55, "0_54", true);
		setup(56, "0_55", true);
//		setup(57, "0_00", true);
//		setup(58, "0_00", true);
//		setup(59, "0_00", true);
//		setup(60, "0_00", false);
		setup(61, "0_60", false);
		setup(62, "0_61", false);
		setup(63, "0_62", false);
		setup(64, "0_63", false);
//		setup(65, "0_00", false);
//		setup(66, "0_00", false);
//		setup(67, "0_00", false);
//		setup(68, "0_00", false);
//		setup(69, "0_00", false);
		setup(70, "0_69", false);
		setup(71, "0_70", false);
		setup(72, "0_71", false);
		setup(73, "0_72", false);
		setup(74, "0_73", false);
//		setup(75, "0_00", false);
//		setup(76, "0_00", false);
//		setup(77, "0_00", false);
//		setup(78, "0_00", false);
		setup(79, "0_78", true);

		
		
		
	}
	public void  setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);//48
			tile[index].collision = collision;
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public void loadMap(String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
		
		
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			
			worldCol++;

			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
			
		}
		
	}
}
