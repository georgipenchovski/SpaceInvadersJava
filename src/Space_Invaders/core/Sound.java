package Space_Invaders.core;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    private Clip soundClip;

    public Sound(String path) {
        try {
            URL url = getClass().getResource(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            soundClip = (Clip) AudioSystem.getLine(info);
            soundClip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        soundClip.start();
    }

    public void loop() {
        soundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        soundClip.stop();
        soundClip.setFramePosition(1);
    }

}


