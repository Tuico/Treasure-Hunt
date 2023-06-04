package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	

	
	public OBJ_Key(GamePanel gp){
		super(gp);
		
		name = "Key";
		down1 = setup("/object/golden_key");
		left1 = setup("/object/silver_key");
		collision = true;
		
		solidArea.x = 16;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
