package entity;

import main.GamePanel;
import java.util.Random;


public class NPC_OldMan extends Entity{

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "right";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
		
	}
	public void setDialogue() {
		
		dialogues[0] = "Oi musta gaw?";
		dialogues[1] = "Naa koy butang gaw";
		dialogues[2] = "Pila imu gaw?";
		dialogues[3] = "Who says??";
		
	}
	public void setAction() {
		
		int sec = 60;
		
		actionLockCounter++;
		
		if(actionLockCounter > (sec*4)) {
			//Assign Movement
				switch(direction) {
			case "up":
				direction = "left";
				break;
			case "down":
				direction = "right";
				break;
			case "left":
				direction = "down";
				break;
			case "right":
				direction = "up";
				break;
			}
			actionLockCounter = 0;
		}
		
		
	}
			
			//Random Movement
//			Random random = new Random();
//			 i = random.nextInt(100)+1; //pick a number randomly from 1 to 100
//			
//			if(i <= 25) {
//				direction = "up";
//			}
//			if(i > 25 && i <= 50) {
//				direction = "down";
//			}
//			if(i > 50 && i <= 75) {
//				direction = "left";
//			}
//			if(i > 75 && i <= 100) {
//				direction = "right";
//			}
			
			
	public void speak() {
		
		super.speak();
	}
	
}
