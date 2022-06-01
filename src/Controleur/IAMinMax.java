package Controleur;

import Modele.Brand;
import Modele.Jeu;
import Modele.Type;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class IAMinMax {
    public Jeu j;
    // Type t;

    public IAMinMax(Jeu j) {
        this.j = j;
    }

    public int IAMinMaxPlayerCard() {
        boolean peutAtout = j.avoiratout;
        if (j.getPlayerfirst() == 1) {
            ConfigIA config = new ConfigIA(j);
            return minMax(config);
        } else {
            int Bmin = -1;
            int Batout = -1;
            if (peutAtout & j.getFirstPlayerPlayCard().getInttype() == j.atout.getInttype()){

                for (int index = 0; index < j.playercard[1].size(); index++) {
                         if (j.playercard[1].get(index).getInttype() == j.atout.getInttype() & (j.playercard[1].get(index).getNum() > j.getFirstPlayerPlayCard().getNum() )){
                             Batout = index;
                         }else if (Bmin != -1 ) {
                             if (j.playercard[1].get(index).getNum() < j.playercard[1].get(Bmin).getNum())
                             Bmin = index;
                         }else if (Bmin == -1) {
                             Bmin = index;
                         }
                    }
            }else{
                for (int index = 0; index < j.playercard[1].size(); index++) {
                    if (j.playercard[1].get(index).getInttype() == j.getFirstPlayerPlayCard().getInttype() & (j.playercard[1].get(index).getNum() > j.getFirstPlayerPlayCard().getNum() )){
                        Batout = index;
                    }else if (Bmin != -1) {
                         if( j.playercard[1].get(index).getNum() < j.playercard[1].get(Bmin).getNum())
                        Bmin = index;
                    }else if (Bmin == -1) {
                        Bmin = index;
                    }
                }
            }
            if (Batout != -1){
                return Batout;
            }
            return Bmin;
        }
    }

    public int minMax(ConfigIA c){
        int meilleurCoup = -1;
        int valeur = -30;
        int val_prec = -30;
            for (int index = 0; index < j.playercard[1].size(); index++){
                valeur = max(valeur,minMaxJoueur(c,index));
                if ( valeur != val_prec){
                    val_prec = valeur;
                    meilleurCoup = index;
                }
            }
        return meilleurCoup;
    }

    public int minMaxJoueur(ConfigIA c,int index){
        int valeur = -100;
        int val_prec = -100;
        int meilleurCoup;
        for (int indexJ = 0; indexJ < j.playercard[0].size(); indexJ++){
            valeur = max(valeur,jouer(index,indexJ,c));
            if ( valeur != val_prec){
                val_prec = valeur;
            }
        }
        return valeur;
    }


    public int jouer(int indexIA, int indexJoueur, ConfigIA conf){

        ConfigIA c = (ConfigIA) conf.cloneConf();
        c.jeu.playercard[1].get(indexIA);
        c.jeu.playercard[0].get(indexJoueur);
        c.jeu.playercard[1].set(indexIA,getBestBrand(conf));
        c.jeu.playercard[0].set(indexJoueur,getBestBrand(conf));

        return c.evaluate();
    }

    public Brand getBestBrand(ConfigIA c){
        Brand bestBrand = null;
        if (c.jeu.avoiratout) {
            Brand atout = c.jeu.atout;
            for (int i = 0; i <= 5; i++) {
                if (c.jeu.pilescard[i].size() > 0) {
                    if (bestBrand == null) {
                        bestBrand = c.jeu.pilescard[i].get(0);
                    } else if (bestBrand != null) {
                        if (atout.getInttype() == bestBrand.getInttype()) {
                            if (atout.getInttype() == c.jeu.pilescard[i].get(0).getInttype() & bestBrand.getNum() < c.jeu.pilescard[i].get(0).getNum()) {
                                bestBrand = c.jeu.pilescard[i].get(0);
                            }
                        } else {
                            if (atout.getInttype() == c.jeu.pilescard[i].get(0).getInttype()) {
                                bestBrand = c.jeu.pilescard[i].get(0);
                            } else {
                                if (bestBrand.getNum() < c.jeu.pilescard[i].get(0).getNum()) {
                                    bestBrand = c.jeu.pilescard[i].get(0);
                                }
                            }
                        }
                    }
                }
            }
        }else{
            for (int i = 0; i <= 5; i++) {
                if (c.jeu.pilescard[i].size() > 0) {
                    if (bestBrand == null) {
                        bestBrand = c.jeu.pilescard[i].get(0);
                    } else if (bestBrand != null & bestBrand.getNum() < c.jeu.pilescard[i].get(0).getNum()) {
                        bestBrand = c.jeu.pilescard[i].get(0);
                    }
                }
            }
        }
        return bestBrand;
    }
}
