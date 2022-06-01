package Controleur;

import Modele.Jeu;

import java.util.concurrent.RecursiveTask;

public class ConfigIA implements Cloneable {
    Jeu jeu;
    int scoreIA ;
    int scoreJoueur;
    int playerNow;

    ConfigIA(Jeu j){
        jeu = (Jeu) j.clone();
        scoreIA = j.getPlayer2Score();
        scoreJoueur = j.getPlayer1Score();
        playerNow = j.getPlayerNow();
    }

    // Evalue la valeur d'une configuration
    public int evaluate(){
        int cartefortes = 0;
        if (jeu.avoiratout) {
            for (int index = 0; index < jeu.playercard[1].size(); index++) {
                if (jeu.playercard[1].get(index).getInttype() == jeu.atout.getInttype()) {
                    for (int index1 = 0; index1 < jeu.playercard[0].size(); index1++) {
                        if ((jeu.playercard[0].get(index1).getInttype() == jeu.atout.getInttype() & jeu.playercard[0].get(index1).getNum() < jeu.playercard[1].get(index).getNum())||jeu.playercard[0].get(index1).getInttype() != jeu.atout.getInttype())
                            cartefortes++;
                    }
                }else {
                    for (int index1 = 0; index1 < jeu.playercard[0].size(); index1++) {
                        if (jeu.playercard[0].get(index1).getInttype() != jeu.atout.getInttype() & jeu.playercard[0].get(index1).getNum() < jeu.playercard[1].get(index).getNum())
                            cartefortes++;
                    }
                }
            }
        }else{
            for (int index = 0; index < jeu.playercard[1].size(); index++) {
                for (int index1 = 0; index1 < jeu.playercard[0].size(); index1++) {
                    if (jeu.playercard[0].get(index1).getInttype() == jeu.playercard[1].get(index).getInttype() & jeu.playercard[0].get(index1).getNum() < jeu.playercard[1].get(index).getNum())
                        cartefortes++;
                }
            }
        }

        return (scoreIA - scoreJoueur) + cartefortes;

    }

    // retourne la phase du jeu
    public int phase(){
        if (scoreJoueur + scoreIA < 30){
            return 1;
        }else
            return 2;
    }

    public Object cloneConf(){
        ConfigIA conf = new ConfigIA(jeu);
        conf.scoreIA = scoreIA;
        conf.scoreJoueur = scoreJoueur;
        conf.playerNow = playerNow;
        return conf;
    }

}
