package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {
	
public OBJ_Door(GamePanel gp){
		
		super(gp);
		
		name = "Door_no_lock";
		up1 = setup("/object/door_up_left");
		left1 = setup("/object/door_left2");
		
		
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
