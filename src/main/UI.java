package main;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;


public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font mainFont;
	BufferedImage heart_full, heart_half, heart_blank, goldenKey, silverKey;
	public boolean messageOn = false;
	public String message = "";
	public int messageCounter = 0;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public double playTime;
	public double limitTime = 999;
	public double bestTime = 999;
	public DecimalFormat deFormat = new DecimalFormat("#0.00");
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/Silver.ttf");
			mainFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
		Entity key = new OBJ_Key(gp);
		goldenKey = key.down1;
		silverKey = key.left1;
		
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
				
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(mainFont);
		g2.setColor(Color.WHITE);
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//SETTINGS STATE
		if(gp.gameState == gp.settingsState) {
			drawSettingScreen();
		}
		//ENDGAME STATE
		if(gp.gameState == gp.endGameState) {
			drawEndGame();
//			drawLeaderboard();
		}
		//LEADERBOARD STATE
//		if(gp.gameState == gp.leaderboardState) {
//			drawLeaderboard();
//		}
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawKey();
			drawTime();
			notif();
			if(gp.player.life == 0) {
				gp.gameState = gp.gameOverState;
				gp.player.life = gp.player.maxLife;
			}
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawKey();
//			drawTime();
			drawPauseScreen();
		}
		//DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawKey();
			drawDialogueScreen();
		}
		if(gp.gameState == gp.gameOverState) {
			drawGameOver();
		}
		
	}
	public void drawGameOver() {
		int x = gp.tileSize*4;
		int y = gp.tileSize* 3;
		int width = gp.screenWidth/2;
		int height = gp.screenHeight/2;
		drawSubWindow(x, y, width, height);
		
		g2.setColor(Color.RED);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "GAME OVER";
		x = setXCenter(text);
		y += gp.tileSize*2;
		g2.drawString(text,  x, y);
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		text = "[Press ENTER to exit]";
		x = setXCenter(text);
		y += gp.tileSize*2;
		g2.drawString(text, x, y);
	}
	public void drawEndGame() {
		
		int x = gp.tileSize*4;
		int y = gp.tileSize* 3;
		int width = gp.screenWidth/2;
		int height = gp.screenHeight/2;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		String text = "YOU FOUND A TREASURE";
		x = setXCenter(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.YELLOW);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		text = "CONGRATULATION";
		x = setXCenter(text);
		y += gp.tileSize*2;
		g2.drawString(text,  x, y);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		y += gp.tileSize;
		g2.drawString("Time: " + deFormat.format(playTime), x, y);
		
		text = "[Press ENTER to exit]";
		x = setXCenter(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
	}
	public void drawLeaderboard() {
		int x = gp.tileSize*4;
		int y = gp.tileSize;
		int width = gp.screenWidth/2;
		int height = gp.screenHeight - gp.tileSize*2;
		drawSubWindow(x, y, width, height);
		
		x += gp.tileSize + gp.tileSize/2;
		y += gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		g2.drawString("LEADERBOARD",  x + gp.tileSize, y);
		
		x -= gp.tileSize;
		y += gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		g2.drawString("Rank", x  , y);
		x += gp.tileSize*2;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		g2.drawString("Name", x + gp.tileSize/2 , y);
		x += gp.tileSize*2;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		g2.drawString("Time", x + gp.tileSize, y);
		
	}
	public void drawKey() {
		
		
		int x = gp.tileSize/2;
		int y = 64;
		
		g2.drawImage(goldenKey, x , y, null);
		
		y += 48;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		g2.drawString("x " + gp.player.hasGoldenKey, x + gp.tileSize, y - 16);
		
//		g2.drawImage(silverKey, x , y, null);
		
//		y += 48;
//		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
//		g2.drawString("x " + gp.player.hasSilverKey, x + gp.tileSize, y - 16);
		
		
	}
	public void drawTime() {
		int x = gp.screenWidth - gp.tileSize*3;
		int y = gp.tileSize;
		playTime += (double)1/60;
		g2.drawString("Time: " + deFormat.format(playTime), x, y);
		
		if(bestTime != 999) {
			String text = "Best Time: ";
			x = setXCenter(text);
			g2.drawString(text + deFormat.format(bestTime), x, y);
		}
		
	
		
	}
	public void notif() {
		if(messageOn == true) {
			
			int x = gp.tileSize/2;
			int y = gp.tileSize*5;

			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
			g2.drawString(message, x + gp.tileSize, y - 16);
			
			messageCounter++;
			if(messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}
			
			
			
		
	}
	public void drawPlayerLife() {
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		//Draw max life
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//Draw current life
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		
	}
	public void drawSettingScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		
		String text = "SETTINGS";
		int x = setXCenter(text);
		int y = gp.screenHeight/2 - gp.tileSize*4;
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		
		
		text = "VOLUME UP";
		x = setXCenter(text);
		y = gp.screenHeight/2 + gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "VOLUME DOWN";
		x = setXCenter(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "BACK";
		x = setXCenter(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	public void drawTitleScreen() {
		//Background
//		g2.setColor(Color.black);
//		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//Title Name
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,120F));
		String text = "TREASURE ADVENTURE";
		int x = setXCenter(text);
		int y = gp.tileSize*3;
		
		//Shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+3, y+3);
		//Main Color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//Draw Character
//		x = gp.screenWidth/2 - gp.tileSize;
//		y = gp.screenHeight/2 - gp.tileSize/2;
//		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2 , null);
		//Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		
		text = "PLAY GAME";
		x = setXCenter(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "SETTINGS";
		x = setXCenter(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "QUIT";
		x = setXCenter(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
	}
	public void drawPauseScreen() {
		
		
		int width = gp.screenWidth;
		int height = gp.screenHeight;
		int xW = gp.screenWidth/2 - (width/2);
		int yW = gp.screenHeight/2 - (height/2);
		drawSubWindow(xW, yW, width, height);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		String text = "PAHUWAY SA GAW";
		int x = setXCenter(text);
		int y = gp.screenHeight/2 - gp.tileSize;
		g2.drawString(text, x, y);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		
		text = "EXIT";
		x = setXCenter(text);
		y = gp.screenHeight/2 + gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize/2, y);
		}
		
	}
	public void drawDialogueScreen() {
		
		int x = gp.tileSize*2;
		int y = gp.tileSize*8;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.screenHeight/4;
		drawSubWindow(x, y, width, height);
		
		//Dialogue text
		g2.setFont(g2.getFont().deriveFont(40F));
		x += gp.tileSize ;
		y += gp.tileSize ;
		
		//Line break
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 25;
		}
		
		
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,180);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 30, 30);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x+3, y+3, width-6, height-6, 25, 25);
	}
	public int setXCenter(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		
		return x;
	}
	public void bestPlayTime() {
		gp.save.load();
		System.out.println(gp.save.Time);
		double recordedTime = Double.parseDouble(gp.save.Time);
		
		if(recordedTime < bestTime) {
			bestTime = recordedTime;
		}
	}
	public void storeBestTime() {
		if(playTime < bestTime) {
			bestTime = playTime;
			gp.save.save(bestTime);
		}
	}
}
