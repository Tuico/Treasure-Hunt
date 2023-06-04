package entity;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Door_Right;
import object.OBJ_Door_Left;
import data.SaveData;


public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public final int spawnPositionX = gp.tileSize * 25;
	public final int spawnPositionY = gp.tileSize * 35;
	
	public int hasSilverKey = 0;
	public int hasGoldenKey = 0;
	public boolean interact = false;
	public boolean interactCD = false;
	public int interactCounter;
	SaveData save = new SaveData(gp);

	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);

		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.height= 16;
		solidArea.width = 30;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		//Player Spawn Location
		worldX = spawnPositionX;
		worldY = spawnPositionY;
		speed = 4;
		direction = "down";
		
		maxLife = 2;
		life = maxLife;
	}
	public void getPlayerImage() {
		
		
		down1 = setup("/player/00_Kid01_walk");
		down2 = setup("/player/01_Kid01_walk");
		down3 = setup("/player/02_Kid01_walk");
		down4 = setup("/player/03_Kid01_walk");
		left1 = setup("/player/04_Kid01_walk");
		left2 = setup("/player/05_Kid01_walk");
		left3 = setup("/player/06_Kid01_walk");
		left4 = setup("/player/07_Kid01_walk");
		right1 = setup("/player/08_Kid01_walk");
		right2 = setup("/player/09_Kid01_walk");
		right3 = setup("/player/10_Kid01_walk");
		right4 = setup("/player/11_Kid01_walk");
		up1 = setup("/player/12_Kid01_walk");
		up2 = setup("/player/13_Kid01_walk");
		up3 = setup("/player/14_Kid01_walk");
		up4 = setup("/player/15_Kid01_walk");
		idle1 = setup("/player/00_Kid01_walk");
		idle2 = setup("/player/01_Kid01_walk");
		idle3 = setup("/player/02_Kid01_walk");
		idle4 = setup("/player/03_Kid01_walk");
	}
	
	
	public void update() {
		
		
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			
				if(keyH.upPressed == true) {
					direction = "up";
				}
				else if(keyH.downPressed == true) {
					direction = "down";
				}
				else if(keyH.leftPressed == true) {
					direction = "left";
				}
				else if(keyH.rightPressed == true) {
					direction = "right";
				}
				collisionOn = false;
				
				gp.cChecker.checkTile(this);
				
				//Checking the object collision
				int objIndex = gp.cChecker.checkObject(this, true);
				pickUpObject(objIndex);
				
				//Check NPC collision
				int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
				interactNPC(npcIndex);
				
				//Check Monster Collision
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				contactMonster(monsterIndex);
				//Checking the tile collision
				
				
				//If collision is false, player can move
				if(collisionOn == false && keyH.enterPressed == false) {
					
					switch(direction) {
					case "up":
						worldY -= speed; break;
					case "down":
						worldY += speed; break;
					case "left":
						worldX -= speed; break;
					case "right":
						worldX += speed; break;
					}
				}keyH.enterPressed = false;
			}
			//player animation
			spriteCounter ++;
			if(spriteCounter > 10) {
				switch(spriteNum) {
				case 1: spriteNum = 2; break;
				case 2: spriteNum = 3; break;
				case 3: spriteNum = 4; break;
				case 4: spriteNum = 1; break;
				}
				
				spriteCounter = 0;
			}
			
		
			if(immune == true) {

				immuneCD++;
				//immune cool down for 2sec
				if(immuneCD > 120) {
					
					immune = false;
					immuneCD = 0;
				}
			}
			if(interactCD == true) {
				interactCounter++;
				if(interactCounter > 60) {
					interact = true;
					interactCD = false;
					interactCounter = 0;
					
				}
			}
			
		}
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Chest":
				gp.stopMusic();
				gp.ui.storeBestTime();
				gp.playSE(6);
				gp.gameState = gp.endGameState;
				break;
			case "GoldenKey":
				hasGoldenKey++;
				gp.ui.showMessage("agoy yawi gaw ayy");
				gp.obj[i] = null;
				break;
			case "SilverKey":
				hasSilverKey++;
				gp.ui.showMessage("ayy ka poor silver rah");
				gp.obj[i] = null;
			case "Door_lock":
				//If the player has golden key then the door unlock			
				if(gp.keyH.enterPressed == true) {
					if(hasGoldenKey > 0) {
						if(doorSwitch == false) {
							gp.ui.showMessage("Ahh shyet easy open gaw");
							gp.obj[i].direction = "up";
							doorCollisionChange(i);
							doorSwitch = true;
							hasGoldenKey--;
						}else if(doorSwitch == true) {
							doorCollisionChange(i);
							if(gp.obj[i].doorType == 1) {
								gp.obj[i].direction = "right";
							}
							if(gp.obj[i].doorType == 2) {
								gp.obj[i].direction = "left";
							}
							
							doorSwitch = false;
							
						}
					}else if(hasGoldenKey == 0) {
					//notify the player he has no key
						gp.playSE(4);
						gp.ui.showMessage("Walay yawi gaw");
						
						if(hasSilverKey > 0) {
							gp.ui.showMessage("Murag di mn ni mao gaw");
						}
					}
//					interact = false;
				}
				
				
				break;
			case "Door_no_lock":
				if(gp.keyH.enterPressed == true) {
					
					if(doorSwitch == false) {
						gp.ui.showMessage("abli ang pultahan");
						gp.obj[i].direction = "up";
						doorCollisionChange(i);
						doorSwitch = true;
					}else if(doorSwitch == true) {
						doorCollisionChange(i);
						if(gp.obj[i].doorType == 1) {
							gp.obj[i].direction = "right";
						}
						if(gp.obj[i].doorType == 2) {
							gp.obj[i].direction = "left";
						}
						
						doorSwitch = false;
					}
					
//					interact = false;
				}
				break;
			}
			
		}
	}
	public void doorCollisionChange(int i) {
		
		if(doorSwitch == false) {
			if(gp.obj[i].doorType == 1) {
				gp.obj[i].solidArea.x = 34;
				gp.obj[i].solidArea.y = 0;
				gp.obj[i].solidArea.width = 10;
				gp.obj[i].solidArea.height = 40;
				gp.obj[i].solidAreaDefaultX = gp.obj[i].solidArea.x;
				gp.obj[i].solidAreaDefaultY = gp.obj[i].solidArea.y;
			}
			else if(gp.obj[i].doorType == 2) {
				gp.obj[i].solidArea.x = 0;
				gp.obj[i].solidArea.y = 0;
				gp.obj[i].solidArea.width = 10;
				gp.obj[i].solidArea.height = 40;
				gp.obj[i].solidAreaDefaultX = gp.obj[i].solidArea.x;
				gp.obj[i].solidAreaDefaultY = gp.obj[i].solidArea.y;	
			}
		}else if(doorSwitch == true) {
			gp.obj[i].solidArea.x = 0;
			gp.obj[i].solidArea.y = 16;
			gp.obj[i].solidArea.width = 44;
			gp.obj[i].solidArea.height = 30;
			gp.obj[i].solidAreaDefaultX = solidArea.x;
			gp.obj[i].solidAreaDefaultY = solidArea.y;
		}
		
	}
	public void interactNPC(int i) {
		
		if(i != 999) {
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed = false;
	}
	public void contactMonster(int i) {
		
		
		
		if(i != 999) {

			//if player gets in contact with monster player receive damage
			if(immune == false) {
				gp.playSE(5);
				life -= 1;
				immune = true;
				}
			
			}
			
			
	}
		
		
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		//draw animation
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
			//If immune is in effect players opacity turns in to 75%
			if(immune == true) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
			}
		//This g2 will draw the player
		g2.drawImage(image, screenX, screenY, null);
		
		//Reset the opacity to 100% if immune is false
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}

}
