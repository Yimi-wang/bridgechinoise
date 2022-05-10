package Modele;

import java.util.ArrayList;
import java.util.LinkedList;

import static Modele.Gameprocess.turnstrat;
import static Modele.Playcards.*;
import static Modele.Starthand.showcard;
import static Modele.Takecard.*;

public class Histoire {
    static LinkedList<DonneeDeHistore> listdehistoire = new LinkedList<>();

    static public void ajoutelistdehistoire(Jeu j, int index) {
        DonneeDeHistore H = new DonneeDeHistore(j, index);
        listdehistoire.add(H);
        System.out.println("put in histoire");
    }

    static public void returnhistoire() {
        DonneeDeHistore H = listdehistoire.get(listdehistoire.size()-1);
        listdehistoire.remove(listdehistoire.size() - 1);
        DonneeDeHistore H2 = listdehistoire.get(listdehistoire.size()-1);
        Jeu oldJeu = H2.getJ();
        showcard(oldJeu);
        switch (H.getGameprocess()) {
            case 1:
                playerfirstplaycard(oldJeu);
                ajoutelistdehistoire(oldJeu, 1);
            case 2:
                playersecondeplaycard(oldJeu);
                ajoutelistdehistoire(oldJeu, 2);
                comparer(oldJeu);
            case 3:
                if (oldJeu.numberOfRounds <= 15) {
                    playerwintakecard(oldJeu);
                    ajoutelistdehistoire(oldJeu, 3);
                }
            case 4:
                if (oldJeu.numberOfRounds <= 15) {
                    playerlosetakecard(oldJeu);
                    ajoutelistdehistoire(oldJeu, 4);
                }
                oldJeu.numberOfRounds++;
                //先后手改变
                oldJeu.playerfirst = oldJeu.Playerwin;
                //得分增加
                if (oldJeu.Playerwin == 0) {
                    oldJeu.Player1Score++;
                } else {
                    oldJeu.Player2Score++;
                }
                System.out.println(("Player 1 score est " + oldJeu.Player1Score + ". Player 2 Score est " + oldJeu.Player2Score));
                turnstrat(oldJeu);
        }

    }

}