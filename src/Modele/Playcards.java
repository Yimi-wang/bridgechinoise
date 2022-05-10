package Modele;

import Controleur.IASimple;
import Controleur.IArandom;

import java.util.Objects;
import java.util.Scanner;

import static Controleur.IASimple.IASimplePlayerCard;
import static Controleur.IArandom.IArandomPlayerCard;
import static Modele.Histoire.returnhistoire;

public class Playcards {
    static void playerfirstplaycard(Jeu j){
        //输入先手方想出的牌
        System.out.println("Maintenant c'est le tour de Jouer"+(j.playerNow+1));
        System.out.println(("Donned le index que vous voulez jouer"));
        Scanner input=new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if(index==-1){
            returnhistoire();
        }
        //进行出牌操作
        playcards(j,index);
        //打印先手方出的牌
        System.out.println((j.playerNow+1)+"jouer"+j.FirstPlayerPlayCard.toString());
    }
    static void playersecondeplaycard(Jeu j){
        //输入后手方想出的牌
        System.out.println("Maintenant c'est le tour de Jouer"+(j.playerNow+1));
        System.out.println(("Donned le index que vous voulez jouer"));
        Scanner input = new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if(index==-1){
            returnhistoire();
        }
        //进行出牌操作
        playcards(j,index);
        //打印后手方出的牌
        System.out.println((j.playerNow+1)+"jouer"+j.SecondPlayerPlayerCard.toString());
    }
    static void IAplaycard(Jeu j,int IA){
        int index = 0;
        //选择IA
        switch(IA){
            case 1:
                index= IArandomPlayerCard(j);
                break;
            case 2:
                index= IASimplePlayerCard(j);

                break;
        }
        System.out.println(index);
        //回退历史记录
        if(index==-1){
            returnhistoire();
        }
        //进行出牌操作
        playcards(j,index);
        //打印IA出的牌
        System.out.println("IA jouer"+j.SecondPlayerPlayerCard.toString());
    }
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
        boolean limite=false;
        for(int i =0; i<j.playercard[j.playerNow].size();i++){
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
                if (j.Playerwin==2){
                j.Playerwin=0;}}
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
