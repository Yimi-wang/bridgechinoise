package Modele;

import java.util.Objects;
import java.util.Scanner;

public class Playcards {
    //实现将玩家选择的牌放到jeu数据里头，同时将该牌从手牌中删除
    static void playcards(Jeu j, int index){
        if (j.playerNow==j.playerfirst){
            j.FirstPlayerPlayCard= j.playercard[j.playerNow].get(index);
            j.playercard[j.playerNow].remove(index);
        }else{
            Brand card=j.playercard[j.playerNow].get(index);
            while(!limite(j,card)){
                System.out.println("L'erreur, tu ne peux pas jouer ce card, reessayer");
                Scanner input=new Scanner(System.in);
                index = input.nextInt();
                card=j.playercard[j.playerNow].get(index);
            }
            j.SecondPlayerPlayerCard = card;
            j.playercard[j.playerNow].remove(index);
        }
    }
    public static boolean limite(Jeu j,Brand card){
        Boolean limite=false;
        for(int i =0; i<=10;i++){
            if (Objects.equals(j.playercard[j.playerNow].get(i).getInttype(), j.FirstPlayerPlayCard.getInttype())){
                limite=true;
                break;
            }
        }
        if(limite){
            return Objects.equals(card.getInttype(), j.FirstPlayerPlayCard.getInttype());
        }
        else return true;
    }

    public static void comparer(Jeu j){
        //如果双方出同花色
        if(Objects.equals(j.FirstPlayerPlayCard.getInttype(), j.SecondPlayerPlayerCard.getInttype())){
            if(j.FirstPlayerPlayCard.getNum()>j.SecondPlayerPlayerCard.getNum())
                j.Playerwin=j.playerfirst;
            else {
                j.Playerwin=j.playerfirst+1;
                if (j.Playerwin==2)
                j.Playerwin=0;}
        }
        //如果双方出不同花色
        else{
            //如果有王牌的话
            if(j.avoiratout){
                //如果后手方出王牌的话
                if(Objects.equals(j.SecondPlayerPlayerCard.getInttype(), j.atout.getInttype())) {
                    j.Playerwin=j.playerfirst+1;
                    if (j.Playerwin==2)
                        j.Playerwin=0;
                    }
                else{//如果后手方不出王牌的话
                    j.Playerwin=j.playerfirst;
                }
            }
            //如果没有王牌的话（默认先手方赢）
            else{
                j.Playerwin=j.playerfirst;
            }

        }
        System.out.println("winner is "+(j.Playerwin+1));
    }

}
