package Modele;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//实现分发手牌
public class Starthand {
    static List<Integer> listcard=new ArrayList<>();;
    static void createPlayingCard(List<Integer> listcard) {
        for(int i=1;i<=52;i++){
            listcard.add(i);
        }
        Collections.shuffle(listcard); //随机打乱list数据

    }
    static void stardhand(Jeu j){
        Brand card = null;
        createPlayingCard(listcard);//
        j.playercard=new List[2];//手牌
        j.pilescard=new List[6];//牌堆
        for(int i =0; i <52; i++){
            switch (listcard.get(i)%13){
                case 0:
                    card = new Brand(Type.pique,new Integer(listcard.get(i)/13));
                    break;
                case 1:
                    card = new Brand(Type.coeur,new Integer(listcard.get(i)/13));
                    break;
                case 2:
                    card = new Brand(Type.carreau,new Integer(listcard.get(i)/13));
                    break;
                case 3:
                    card = new Brand(Type.trefle,new Integer(listcard.get(i)/13));
                    break;
            }
        if(i<11){
            j.playercard[0].add(card);
        }
        else if(i>10 && i<21){
            j.playercard[1].add(card);
        }
        else{
            j.pilescard[(i-21)%6].add(card);
        }
        }
    }


}
