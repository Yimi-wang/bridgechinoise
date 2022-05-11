package Controleur;

import Modele.Brand;
import Modele.Jeu;

import java.util.Objects;
import java.util.Random;

public class IArandom2 {
    public Jeu j;
    public IArandom2(Jeu j){
        this.j=j;
    }
    public int IArandomPlayerCard2(Jeu j){
        Random r = new Random();
        if (j.getPlayerNow()==0){
            if(j.getPlayerfirst()==j.getPlayerNow()){
                return r.nextInt(j.playercard[0].size());
            }
            else{
                int index=r.nextInt(j.playercard[0].size());
                System.out.println("le index est:" + index);
                while(limite(j,j.getFirstPlayerPlayCard())&&(!Objects.equals(j.playercard[0].get(index).getInttype(), j.getFirstPlayerPlayCard().getInttype()))){
                    index=r.nextInt(j.playercard[0].size());
                }
                return index;
            }
        }
        return -1;
    }

    public int IarandomGetCard2(){
        Random r = new Random();
        int index =r.nextInt(6);
        if(j.getPlayerNow()==0){
            while (j.pilescard[index].size()==0){
                index =r.nextInt(6);
            }
        }
        return index;
    }

    public boolean limite(Jeu j, Brand card) {
        boolean limite = false;
        for (int i = 0; i < j.playercard[j.getPlayerNow()].size(); i++) {
            if (Objects.equals(j.playercard[j.getPlayerNow()].get(i).getInttype(), j.getFirstPlayerPlayCard().getInttype())) {
                limite = true;
                break;
            }
        }
        if (limite) {
            return Objects.equals(card.getInttype(), j.getFirstPlayerPlayCard().getInttype());
        } else return true;
    }
}
