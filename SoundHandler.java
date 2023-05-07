import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.awt.event.*;
import java.util.HashMap;

public class SoundHandler {
    public static class Sound
    {
        File file;

        public Sound(File file)
        {
            try {
                this.file = file;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void play() {
            try {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(sound);
                clip.start();

                int delay = 4000; //milliseconds
                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            sound.close();
                            clip.close();
                            clip.stop();   
                        } catch(Exception e) {
            
                        }
                    }
                };
                Timer timer = new Timer(delay, taskPerformer);
                timer.setRepeats(false);
                timer.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    private static  HashMap<String, Sound> sounds = new HashMap<String, Sound>();

    public static void LoadSound(String name, String filename)
    {
        try {
            File file = new File(filename);
            Sound sound = new Sound(file);
            sounds.put(name, sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void PlaySound(String name)
    {
        sounds.get(name).play();
    }
}
