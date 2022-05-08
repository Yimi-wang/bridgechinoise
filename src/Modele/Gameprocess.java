package Modele;
import Modele.Jeu;


public class Gameprocess {
    Jeu j = new Jeu();
    int i =j.getNumberOfGames();
    if (i>10){
        i=1;
    }

}
