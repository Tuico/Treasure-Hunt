package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;


public class Entity {
	
	GamePanel gp;
	

	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, up3, up4, 
						down1, down2, down3, down4, 
						left1, left2, left3, left4, 
						right1, right2, right3, right4,
						idle1, idle2, idle3, idle4;;
	public String direction = "idle";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 16, 30);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter, doorDelay;
	public boolean immune = false;
	public int immuneCD = 0;
	String dialogues[] = new String[20];
	public int dialogueIndex = 0;
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int type;// Tells what type of entity
	public int doorType; // 1 = door right and 2 = door left
	public boolean doorSwitch = false;
	
	
	
	//Player Health
	public int maxLife;
	public int life;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void setAction() {}
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
		direction = "idle";
		
	}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		//If type = monster and player is in contact
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.immune == false) {
				gp.playSE(5);
				gp.player.life -= 1;
				gp.player.immune = true;
			}
		}
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		spriteCounter ++;
		
		if(spriteCounter > 12) {
			//Animation
			switch(spriteNum) {
			case 1: spriteNum = 2; break;
			case 2: spriteNum = 3; break;
			case 3: spriteNum = 4; break;
			case 4: spriteNum = 1; break;
			}
			spriteCounter = 0;
		}
		
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		//if the entity or monster is with in the map or the screen then the entity can move
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
				switch(direction){
				case "up":
					if(spriteNum == 1) {
						image = up1;
					}else if (spriteNum == 2) {
						image = up2;
					}else if (spriteNum == 3) {
						image = up3;
					}else if (spriteNum == 4) {
						image = up4;
					}
					break;
				case "down":
					if(spriteNum == 1) {
						image = down1;
					}else if (spriteNum == 2) {
						image = down2;
					}else if (spriteNum == 3) {
						image = down3;
					}else if (spriteNum == 4) {
						image = down4;
					}
					break;
				case "left":
					if(spriteNum == 1) {
						image = left1;
					}else if (spriteNum == 2) {
						image = left2;
					}else if (spriteNum == 3) {
						image = left3;
					}else if (spriteNum == 4) {
						image = left4;
					}
					break;
				case "right":
					if(spriteNum == 1) {
						image = right1;
					}else if (spriteNum == 2) {
						image = right2;
					}else if (spriteNum == 3) {
						image = right3;
					}else if (spriteNum == 4) {
						image = right4;
					}
					break;
				case "idle":
					if(spriteNum == 1) {
						image = idle1;
					}else if (spriteNum == 2) {
						image = idle2;
					}else if (spriteNum == 3) {
						image = idle3;
					}else if (spriteNum == 4) {
						image = idle4;
					}
					break;
					
			}
			// draw the animation
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	//Main set up for image
	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			//use to get the file image
			image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
