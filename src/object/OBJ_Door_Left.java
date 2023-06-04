package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Left extends Entity {
	
public OBJ_Door_Left(GamePanel gp){
		
		super(gp);
		
		name = "Door_no_lock";
		doorType = 2;
		direction = "left";
		up1 = setup("/object/door_up_left");
		left1 = setup("/object/door_left2");
		
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
