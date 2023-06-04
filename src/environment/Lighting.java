package environment;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;


import main.GamePanel;

public class Lighting {
	
	GamePanel gp;
	BufferedImage darknessFilter;

	public Lighting(GamePanel gp, int circleSize) {
		
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics(); 
		
		int centerX = gp.player.screenX + (gp.tileSize)/2;
		int centerY = gp.player.screenY + (gp.tileSize)/2;
		
		//Create a gradation effect
		Color color[] = new Color[10];
		float fraction[] = new float[10];
		
		//the opacity of the color fades
		color[0] = new Color(0,0,0,0f);
		color[1] = new Color(0,0,0,0.2f);
		color[2] = new Color(0,0,0,0.25f);
		color[3] = new Color(0,0,0,0.3f);
		color[4] = new Color(0,0,0,0.43f);
		color[5] = new Color(0,0,0,0.5f);
		color[6] = new Color(0,0,0,0.6f);
		color[7] = new Color(0,0,0,0.7f);
		color[8] = new Color(0,0,0,0.8f);
		color[9] = new Color(0,0,0,0.99f);
		
		//the distance of each color 
		fraction[0] = 0f;
		fraction[1] = 0.3f;
		fraction[2] = 0.4f;
		fraction[3] = 0.5f;
		fraction[4] = 0.6f;
		fraction[5] = 0.7f;
		fraction[6] = 0.8f;
		fraction[7] = 0.85f;
		fraction[8] = 0.9f;
		fraction[9] = 1f;
		
		//Gradation paint setting for light circle
		RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);
		
		//set gradient data on Graphics2D
		g2.setPaint(gPaint);
		
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.dispose();
	}
	public void draw(Graphics2D g2) {
		
		g2.drawImage(darknessFilter, 0,0, null);
	}
}
