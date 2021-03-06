package Vue;
import javax.sound.sampled.*;
import java.io.*;


public class JavaClip {
    private static Clip bgm;
    private static Clip hit;
    private static AudioInputStream ais;

    JavaClip() {
    }

    public static void playmainmusic() {
        try {
            bgm = AudioSystem.getClip();
            InputStream is = JavaClip.class.getClassLoader().getResourceAsStream("backmusic.wav");
            BufferedInputStream bfi = new BufferedInputStream(is);
            if (bfi != null) {
                ais = AudioSystem.getAudioInputStream(bfi);

            }
            bgm.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        bgm.start();
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void playgamemusic() {
        try {
            bgm = AudioSystem.getClip();
            InputStream is = JavaClip.class.getClassLoader().getResourceAsStream("gamemusic.wav");
            BufferedInputStream bfi = new BufferedInputStream(is);
            if (bfi != null) {
                ais = AudioSystem.getAudioInputStream(bfi);

            }
            bgm.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        bgm.start();
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stop() {
        if (ais != null)
            bgm.close();
    }
    public static void stopplaymusic() {
        try {
            bgm = AudioSystem.getClip();
            InputStream is = JavaClip.class.getClassLoader().getResourceAsStream("res/music/gamemusic.wav");
            if (is != null) {
                ais = AudioSystem.getAudioInputStream(is);

            }
            bgm.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        bgm.close();
    }
}
