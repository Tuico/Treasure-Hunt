package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	public float musicVolume = (float) -0.0;
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/Gradual Collapse.wav");
		soundURL[1] = getClass().getResource("/sound/Dungeon.wav");
		soundURL[2] = getClass().getResource("/sound/dooropen.wav");
		soundURL[3] = getClass().getResource("/sound/door_close.wav");
		soundURL[4] = getClass().getResource("/sound/door_need_key.wav");
		soundURL[5] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[6] = getClass().getResource("/sound/fanfare.wav");
		
	}
	public void setFile(int i) {
		//To open audio file in java
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
			
		}catch(Exception e){
			
		}
	}
	public void play() {
		
		clip.start();
	}
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	public void stop() {
	
		clip.stop();
		
	}
	public void control() {
		
		//Reduce music volume
		FloatControl gainControl = 
		    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(musicVolume); // Reduce volume by decibels.
	}
}
