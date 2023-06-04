package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Left_Lock extends Entity {
	
public OBJ_Door_Left_Lock(GamePanel gp){
		
		super(gp);
		
		name = "Door_lock";
		doorType = 2;
		direction = "left";
		up1 = setup("/object/door_up_left");
		left1 = setup("/object/door_left");
		
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}