package Vue;

import Modele.Jeu;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    JFrame jFrame;
    Jeu jeu;

    public GameFrame(Jeu jeu) {
        jFrame = new JFrame("game");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 300);
        this.jeu = jeu;
    }


    public void run() {
        jFrame.setLayout(new BorderLayout());

        JPanel mainPart = new JPanel();
        mainPart.setBackground(Color.blue);
        JPanel menu = new JPanel();
        menu.setBackground(Color.green);
        menu.setPreferredSize(new Dimension( 150, 300));
        jFrame.add(mainPart,"Center");
        jFrame.add(menu, "East");
        mainPart.setLayout(new BorderLayout());
        JPanel partPlayer1 = new JPanel();
        JPanel partPlayer2 = new JPanel();
        JPanel cards = new JPanel();
        JLabel p1 = new JLabel("p1");
        JLabel p2 = new JLabel("p2");
        JLabel card = new JLabel("cards");
        mainPart.add(partPlayer1,"South");
        mainPart.add(partPlayer2,"North");
        mainPart.add(cards,"Center");
        partPlayer1.add(p1);
        partPlayer2.add(p2);
        cards.add(card);

        jFrame.setVisible(true);


    }

    public void partPlayer1(JPanel p1) {
        p1.setLayout();


    }

    public void partPlayer2(JPanel p2) {

    }

    public void partMenu(JPanel menu) {


    }

    public void partCards(JPanel cards) {
        CardLayout cardLayout = new CardLayout();
        cards.setLayout(cardLayout);



    }


}
