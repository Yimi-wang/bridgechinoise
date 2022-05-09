package Modele;
import Modele.Jeu;

import static Modele.Starthand.stardhand;
import static Modele.Atout.*;


public class Gameprocess {
    public static void creatJeu(){
        Jeu j = new Jeu();
        Gamestart(j);
    }

    public static void Gamestart(Jeu j) {
        if (j.numberOfGames == 0) j.numberOfGames = 1;
        if (j.playerfirst == 2) {
            j.playerfirst = (j.numberOfGames - 1) % 2;
            stardhand(j);
        }
        turnstrat(j);
    }

    public static void turnstrat(Jeu j){
        determinierAtout(j);
    }

}
