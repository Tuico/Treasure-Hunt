package monster;

import entity.Entity;
import main.GamePanel;
import java.util.Random;

public class MON_Bat extends Entity {

	public MON_Bat(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "Bat";
		speed = 2;
		maxLife = 3;
		life = maxLife;
		direction = "left";
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 48;
		solidArea.height = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/bat_down_1");
		up2 = setup("/monster/bat_down_2");
		up3 = setup("/monster/bat_down_1");
		up4 = setup("/monster/bat_down_2");
		down1 = setup("/monster/bat_down_1");
		down2 = setup("/monster/bat_down_2");
		down3 = setup("/monster/bat_down_1");
		down4 = setup("/monster/bat_down_2");
		left1 = setup("/monster/bat_down_1");
		left2 = setup("/monster/bat_down_2");
		left3 = setup("/monster/bat_down_1");
		left4 = setup("/monster/bat_down_2");
		right1 = setup("/monster/bat_down_1");
		right2 = setup("/monster/bat_down_2");
		right3 = setup("/monster/bat_down_1");
		right4 = setup("/monster/bat_down_2");
	}
	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter > 180) {
	
//			Assign Movement
			switch(direction) {
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
			//Random Movement
//			Random random = new Random();
//			int i = random.nextInt(100)+1;
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
			actionLockCounter = 0;
		}
		
		
	}
	
}