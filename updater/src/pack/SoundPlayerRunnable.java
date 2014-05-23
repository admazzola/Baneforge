package pack;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SoundPlayerRunnable implements Runnable {

	String s;
	int volume = 100;

		   public SoundPlayerRunnable(String s) {
		      this.s = s;
		   }
		   
		   public SoundPlayerRunnable(String s, int volume) {
			      this.s = s;
			      this.volume=volume;
			   }

		   public void run() { 
			   
			   System.out.println(s);
			   
			   try { // Open an audio input stream.
				   
				   Audio  SFX = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(s));
				   
				   
				   float gain = ((float) volume) / 100f;
				   
				   System.out.println(gain);
				   
				   SFX.playAsSoundEffect(1.0f, gain, false);
				   
				  
					
				  
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
			   
			   
		   }
		
	
	
}
