// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.*;


public class Sounds {
	// Constructor
	AudioFileFormat.Type [] audioFileTypes = AudioSystem.getAudioFileTypes();
	public Sounds() {
		
	}
	
	
	public void audio(String audio) {
		try {
			// Open an audio input stream.
			File soundFile = new File(audio); 

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = audioIn.getFormat();

			// Get a sound clip resource.
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	

}