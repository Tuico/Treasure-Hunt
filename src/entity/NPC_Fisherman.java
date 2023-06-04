package entity;

import main.GamePanel;
import java.util.Random;

public class NPC_Fisherman extends Entity{

	public NPC_Fisherman(GamePanel gp) {
		super(gp);
		
		direction = "idle";
		speed = 1;
		
		getImage();
		setDialogue();
		solidArea.x = 8;
		solidArea.y = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.height= 16;
		solidArea.width = 30;
	}
	public void getImage() {
		
		idle1 = setup("/npc/05_Farmer_walk");
		idle2 = setup("/npc/05_Farmer_walk");
		idle3 = setup("/npc/05_Farmer_walk");
		idle4 = setup("/npc/05_Farmer_walk");
		down1 = setup("/npc/00_Farmer_walk");
		down2 = setup("/npc/01_Farmer_walk");
		down3 = setup("/npc/02_Farmer_walk");
		down4 = setup("/npc/03_Farmer_walk");
		left1 = setup("/npc/04_Farmer_walk");
		left2 = setup("/npc/05_Farmer_walk");
		left3 = setup("/npc/06_Farmer_walk");
		left4 = setup("/npc/07_Farmer_walk");
		right1 = setup("/npc/08_Farmer_walk");
		right2 = setup("/npc/09_Farmer_walk");
		right3 = setup("/npc/10_Farmer_walk");
		right4 = setup("/npc/11_Farmer_walk");
		up1 = setup("/npc/12_Farmer_walk");
		up2 = setup("/npc/13_Farmer_walk");
		up3 = setup("/npc/14_Farmer_walk");
		up4 = setup("/npc/15_Farmer_walk");
		
	}
	public void setDialogue() {
		
		dialogues[0] = "Are you lost?";
		dialogues[1] = "I've been stuck here for days";
		dialogues[2] = "There seems to be  a tresure";
		dialogues[3] = "Can you find it?";
		
	}
	public void setAction() {
		
		int sec = 60;
//		
//		actionLockCounter++;
//		
		if(actionLockCounter > (sec)) {
			
//			//Assign Movement
//				switch(direction) {
//			case "up":
//				direction = "left";
//				break;
//			case "down":
//				direction = "right";
//				break;
//			case "left":
//				direction = "down";
//				break;
//			case "right":
//				direction = "up";
//				break;
//			}
			
			//Random Movement
			Random random = new Random();
			int i = random.nextInt(100)+1; //pick a number randomly from 1 to 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
		
		
	}
			
	public void speak() {
		
		super.speak();
	}
	
}
