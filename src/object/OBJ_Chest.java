package object;


import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {
	
	
	public OBJ_Chest(GamePanel gp){
		
		super(gp);
		
		name = "Chest";
		idle1 = setup("/object/big_chest_01");
		
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
