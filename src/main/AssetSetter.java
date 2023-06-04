package main;

import entity.NPC_Fisherman;
import monster.MON_Bat;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Door_Left;
import object.OBJ_Door_Left_Lock;
import object.OBJ_Door_Right;
import object.OBJ_Door_Right_Lock;
import object.OBJ_Golden_Key;
import object.OBJ_Key;
import object.OBJ_Silver_Key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {

//	Chest Location
		gp.obj[0] = new OBJ_Chest(gp);
		gp.obj[0].worldX = gp.tileSize*8;
		gp.obj[0].worldY = gp.tileSize*8;
//  Key location
		
		
		gp.obj[1] = new OBJ_Golden_Key(gp);
		gp.obj[1].worldX = gp.tileSize*7;
		gp.obj[1].worldY = gp.tileSize*33;
		gp.obj[2] = new OBJ_Golden_Key(gp);
		gp.obj[2].worldX = gp.tileSize*23;
		gp.obj[2].worldY = gp.tileSize*14;
		
		
//	Door location
//		Door no lock
		gp.obj[5] = new OBJ_Door_Left(gp);
		gp.obj[5].worldX = gp.tileSize*25;
		gp.obj[5].worldY = gp.tileSize*30;
		gp.obj[6] = new OBJ_Door_Right(gp);
		gp.obj[6].worldX = gp.tileSize*26;
		gp.obj[6].worldY = gp.tileSize*30;
		gp.obj[7] = new OBJ_Door_Left(gp);
		gp.obj[7].worldX = gp.tileSize*32;
		gp.obj[7].worldY = gp.tileSize*36;
		gp.obj[8] = new OBJ_Door_Right(gp);
		gp.obj[8].worldX = gp.tileSize*33;
		gp.obj[8].worldY = gp.tileSize*36;
//		Door lock
		gp.obj[9] = new OBJ_Door_Left_Lock(gp);
		gp.obj[9].worldX = gp.tileSize*32;
		gp.obj[9].worldY = gp.tileSize*17;
		gp.obj[10] = new OBJ_Door_Right_Lock(gp);
		gp.obj[10].worldX = gp.tileSize*33;
		gp.obj[10].worldY = gp.tileSize*17;
		gp.obj[11] = new OBJ_Door_Left_Lock(gp);
		gp.obj[11].worldX = gp.tileSize*7;
		gp.obj[11].worldY = gp.tileSize*23;
		gp.obj[12] = new OBJ_Door_Right_Lock(gp);
		gp.obj[12].worldX = gp.tileSize*8;
		gp.obj[12].worldY = gp.tileSize*23;
		gp.obj[13] = new OBJ_Door_Left_Lock(gp);
		gp.obj[13].worldX = gp.tileSize*19;
		gp.obj[13].worldY = gp.tileSize*32;
		gp.obj[14] = new OBJ_Door_Right_Lock(gp);
		gp.obj[14].worldX = gp.tileSize*20;
		gp.obj[14].worldY = gp.tileSize*32;

		
		
	}
	public void setNPC() {
		
		
		gp.npc[0] = new NPC_Fisherman(gp);
		gp.npc[0].worldX = gp.tileSize*26;
		gp.npc[0].worldY = gp.tileSize*34;
		
	}
	public void setMonster() {
		
		//x 27 to 38
		//y 38 to 42
		gp.monster[0] = new MON_Bat(gp);
		gp.monster[0].worldX = gp.tileSize*12;
		gp.monster[0].worldY = gp.tileSize*8;
		gp.monster[1] = new MON_Bat(gp);
		gp.monster[1].worldX = gp.tileSize*12;
		gp.monster[1].worldY = gp.tileSize*16;
		gp.monster[2] = new MON_Bat(gp);
		gp.monster[2].worldX = gp.tileSize*22;
		gp.monster[2].worldY = gp.tileSize*16;
		gp.monster[3] = new MON_Bat(gp);
		gp.monster[3].worldX = gp.tileSize*8;
		gp.monster[3].worldY = gp.tileSize*36;
		gp.monster[4] = new MON_Bat(gp);
		gp.monster[4].worldX = gp.tileSize*40;
		gp.monster[4].worldY = gp.tileSize*19;
		gp.monster[5] = new MON_Bat(gp);
		gp.monster[5].worldX = gp.tileSize*32;
		gp.monster[5].worldY = gp.tileSize*33;
		gp.monster[6] = new MON_Bat(gp);
		gp.monster[6].worldX = gp.tileSize*22;
		gp.monster[6].worldY = gp.tileSize*8;

	}

}
