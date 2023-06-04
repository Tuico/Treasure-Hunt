package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Right_Lock extends Entity {
	
	public OBJ_Door_Right_Lock(GamePanel gp){
		
		super(gp);
		
		name = "Door_lock";
		doorType = 1;
		direction = "right";
		up1 = setup("/object/door_up_right");
		right1 = setup("/object/door_right");
		
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}

