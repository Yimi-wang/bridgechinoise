package Modele;

import java.util.LinkedList;

public class HandCardSorting {
    static public void handcardsorting(Jeu j){
        for (int i =0; i < 2; i++){
            LinkedList<Brand> sort=new LinkedList<>();
            Brand card;
            for(int coleur=0;coleur<4;coleur++){
                for (int num = 1; num<14;num++){
                    for (int index =0; index<j.playercard[i].size();index++){
                        if((j.playercard[i].get(index).getInttype()==coleur)&&(j.playercard[i].get(index).getNum()==num)){
                            card=j.playercard[i].get(index);
                            sort.add(card);
                        }
                    }
                }
            }
            j.playercard[i]=sort;
        }
    }
}
