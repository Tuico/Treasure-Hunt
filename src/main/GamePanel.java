package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;
import entity.Entity;
import environment.EnvironmentManager;
import data.SaveData;
import data.Leaderboard;

public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 16;
	final int scale = 3;
	
	//SCREEN SETTINGS
	public final int tileSize = originalTileSize * scale; 
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLD SETTING
	public int maxWorldCol = 50;
	public int maxWorldRow = 50;
	
	
	int FPS = 60;
	
	//SYSTEM CALL
	TileManager tileM = new TileManager(this); 
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Leaderboard lb = new Leaderboard(this);
	public SaveData save = new SaveData(this);
	EnvironmentManager eManager = new EnvironmentManager(this);
	Thread gameThread;
	
	//Entity and object
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[15];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	//Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int settingsState = 4;
	public final int endGameState = 5;
	public final int gameOverState = 6;
	
	public GamePanel() {
			
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void setUpGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		eManager.setup();
		
		playMusic(1);
		gameState = titleState;
	}
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	//DELTA/ACCUMULATOR Method
	public void run() {
		
		double drawInterval = 1000000000/FPS; 
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >=1) {
				update();
				repaint();
				delta--;
			}
		}
		
	}
	//Update
	public void update() {
		
		if(gameState == playState) {
			//Player
			player.update();
			
			
			//NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					monster[i].update();
				}
			}
			
		}
		if(gameState == pauseState) {
			//nothing
			stopMusic();
		}
		if(gameState == gameOverState) {
			
		}
		
	}
	//PAINT
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//TITLE STATE
		if(gameState == titleState) {
			
			ui.draw(g2);
		}
		else{

			//Draw Tile
			tileM.draw(g2);
			
			entityList.add(player);
			
			//Add Entity to the list
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					
					entityList.add(npc[i]);
				}
			}
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					
					entityList.add(obj[i]);
				}
			}
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					
					entityList.add(monster[i]);
				}
			}
			//Sort
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
				
			});
			
			//Draw Entity
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			//Empty Entity list
			entityList.clear();
			
			//draw the environment
			eManager.draw(g2);
			
			
			//UI
			ui.draw(g2);	
			
			g2.dispose();
		}
		
	}

	public void  playMusic(int i) {
		//sets the file 
		music.setFile(i);
		//play the music
		music.play();
		//looping 
		music.loop();
	}
	public void stopMusic() {
		//stops the music
		music.stop();
	}
	public void playSE(int i) {
		
		//this is use for sound effect
		se.setFile(i);
		se.play();
		//no loop
	}
}



