package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TitreInterface extends JComponent{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Graphics2D drawable;

    public Image imgTitle;
    private double proportionTitre;
    private int width;

    public TitreInterface() {
        try {
            imgTitle = ImageIO.read(new File("./bridgechinoise/res/images/title.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(800, 200));
    }

    public void tracerTitre() {
        drawable.drawImage(imgTitle, (int)(width/2 - (200*proportionTitre)),  0, (int)(400*proportionTitre), (int)(200*proportionTitre), null);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.width = getSize().width;
        int height = getSize().height;

        this.proportionTitre = 1.0 * Math.min(width/400.0, height/200.0);
        this.drawable = (Graphics2D) g;
        this.tracerTitre();
    }
}
