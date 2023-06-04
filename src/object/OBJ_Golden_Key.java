package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Golden_Key extends Entity{

	public OBJ_Golden_Key(GamePanel gp){
		super(gp);
		
		name = "GoldenKey";
		idle1 = setup("/OBJECT/Golden_Key");
		
		collision = true;
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}

}
