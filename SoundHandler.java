import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;

public class SoundHandler {
    public static class Sound
    {
        Clip clip;
        AudioInputStream sound;

        public Sound(AudioInputStream sound)
        {
            try {
                this.sound = sound;
                clip = AudioSystem.getClip();
                clip.open(sound);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void play() {
            clip.start();
        }
        public void stop() throws IOException {
            sound.close();
            clip.close();
            clip.stop();
        }
    }

    public void PlaySound()
    {
        // AudioSystem.
        
    }
}
