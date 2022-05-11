package Modele;

public class ResetJeu {
    public static void reset(Jeu j) {
        j.playerfirst=2;
        j.numberOfRounds=0;
        j.Playerwin=2;
        j.Player1Score=0;
        j.Player2Score=0;
    }
}
