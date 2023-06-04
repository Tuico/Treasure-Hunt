package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					//New Game
					gp.ui.bestPlayTime();
					gp.player.worldX = gp.player.spawnPositionX;
					gp.player.worldY = gp.player.spawnPositionY;
					gp.ui.playTime = 0;
					gp.stopMusic();
					gp.gameState = gp.playState;
					gp.playMusic(0);
					
				}else if(gp.ui.commandNum == 1) {
					//Settings
					gp.gameState = gp.settingsState;
					
				}else if(gp.ui.commandNum == 2) {
					//Quit
					System.exit(0);
					
				}
			}
			
		}
		//GameOver state
		if(gp.gameState == gp.gameOverState) {

			if(code == KeyEvent.VK_ENTER) {
				
				gp.gameState = gp.titleState;
			}
		}
			
		//SETTINGS STATE
		if(gp.gameState == gp.settingsState) {
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					//Volume Up
					gp.music.musicVolume += (float) +5.0;
					gp.music.control();
					
				}else if(gp.ui.commandNum == 1) {
					//Volume Down
					gp.music.musicVolume += (float) -5.0;
					gp.music.control();
					
				}else if(gp.ui.commandNum == 2) {
					//Back to title
					gp.gameState = gp.titleState;
					
				}
			}
		}
		//ENDGAME STATE
		if(gp.gameState == gp.endGameState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.titleState;
			}
		}
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_ENTER) {
				gp.player.interactCD = true;
				if(gp.player.interact == true) {
					enterPressed = true;
					gp.player.interact = false;
				}
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.stopMusic();
				gp.gameState = gp.titleState;
			}
			if(code == KeyEvent.VK_R) {
				gp.ui.bestTime = 999;
				gp.ui.showMessage("Na reset nag ang best time gaw");
			}
			if(code == KeyEvent.VK_M) {
				gp.music.musicVolume += (float) -5.0;
				gp.music.control();
			}
			if(code == KeyEvent.VK_N) {
				gp.music.musicVolume += (float) +5.0;
				gp.music.control();
			}
			
			
		}
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			gp.ui.commandNum = 0;
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
			if(code == KeyEvent.VK_ENTER) {
				//Settings
					gp.gameState = gp.titleState;
			}
			
		}
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W ) {
			upPressed = false;
			gp.player.direction = "idle";
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
			gp.player.direction = "idle";
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
			gp.player.direction = "idle";
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
			gp.player.direction = "idle";
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
	}

}
