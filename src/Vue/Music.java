package Vue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

public class Music {
    public Music(){};

    public void menumusic() throws JavaLayerException, FileNotFoundException {
        File file = new File("menumusic.mp3");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream stream = new BufferedInputStream(fis);
        Player player = new Player(stream);
        player.play();
    }

    public void jeumusic() throws JavaLayerException, FileNotFoundException {
        File file = new File("gamemusic.mp3");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream stream = new BufferedInputStream(fis);
        Player player = new Player(stream);
        player.play();
    }

}