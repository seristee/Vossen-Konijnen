package nl.hanze.t12.mvc.Data;

import java.applet.AudioClip;
import java.applet.Applet;

public class Sound{
	
	public static final Sound sound1 = new Sound("/sound.wav");
	private AudioClip clip;
	
	public Sound(String filename){
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(filename));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		try{
			new Thread(){
				public void run(){
					clip.play();
				}
			}.start();
		}catch(Exception ex){		
			ex.printStackTrace();
		}
	}
}