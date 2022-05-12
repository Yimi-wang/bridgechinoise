package Modele;

import Controleur.IASimple;
import Controleur.IArandom;
import Controleur.IArandom2;

import java.util.Objects;
import java.util.Scanner;

public class PlayCards {

    Jeu j;
    Histoire h;

    public PlayCards(Jeu j, Histoire h) {
        this.j = j;
        this.h = h;
    }


    Jeu playerFirstPlayCard() {
        //输入先手方想出的牌
        System.out.println("Maintenant c'est le tour de Jouer" + (j.playerNow + 1));
        System.out.println(("Donned le index que vous voulez jouer"));
        Scanner input = new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if (index == -1) {
            j = h.returnHistoire();
            return j;
        }
        //进行出牌操作
        playCards(index);
        //打印先手方出的牌
        System.out.println((j.playerNow + 1) + "jouer" + j.FirstPlayerPlayCard.toString());
        return j;
    }

    Jeu playerSecondePlayCard() {
        //输入后手方想出的牌
        System.out.println("Maintenant c'est le tour de Jouer" + (j.playerNow + 1));
        System.out.println(("Donned le index que vous voulez jouer"));
        Scanner input = new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if (index == -1) {
            j = h.returnHistoire();
            return j;
        }
        //进行出牌操作
        playCards(index);
        //打印后手方出的牌
        System.out.println((j.playerNow + 1) + "jouer" + j.SecondPlayerPlayerCard.toString());
        return j;
    }

    void IAplaycard(Jeu j,int IA){
        int index = 0;
        //选择IA
        switch(IA){
            case 1:
                IArandom iar=new IArandom(j);
                index= iar.IArandomPlayerCard();
                break;
            case 2:
                IASimple ias= new IASimple(j);
                index= ias.IASimplePlayerCard();
                break;
            case 3:
                IArandom2 iar2 = new IArandom2(j);
                index= iar2.IArandomPlayerCard2(j);
                break;
        }
        System.out.println(index);
        //进行出牌操作
        if(j.playerNow==0){
            System.out.println("IA 1 jouer"+j.playercard[j.playerNow].get(index).toString());
        }else {
            System.out.println("IA 2 jouer"+j.playercard[j.playerNow].get(index).toString());
        }
        playCards(index);
        //打印IA出的牌

    }

    //实现将玩家选择的牌放到jeu数据里头，同时将该牌从手牌中删除
    void playCards(int index) {
        if (j.playerNow == j.playerFirst) {
            j.FirstPlayerPlayCard = j.playercard[j.playerNow].get(index);
            j.playercard[j.playerNow].remove(index);
        } else {
            Brand card = j.playercard[j.playerNow].get(index);
            while (!limite(j, card)) {
                System.out.println("L'erreur, tu ne peux pas jouer ce card, reessayer");
                Scanner input = new Scanner(System.in);
                index = input.nextInt();
                card = j.playercard[j.playerNow].get(index);
            }
            j.SecondPlayerPlayerCard = card;
            j.playercard[j.playerNow].remove(index);
        }
        j.TurnProcess++;
    }

    public boolean limite(Jeu j, Brand card) {
        boolean limite = false;
        for (int i = 0; i < j.playercard[j.playerNow].size(); i++) {
            if (Objects.equals(j.playercard[j.playerNow].get(i).getInttype(), j.FirstPlayerPlayCard.getInttype())) {
                limite = true;
                break;
            }
        }
        if (limite) {
            return Objects.equals(card.getInttype(), j.FirstPlayerPlayCard.getInttype());
        } else return true;
    }

    public void comparer() {
        //如果双方出同花色
        if (Objects.equals(j.FirstPlayerPlayCard.getInttype(), j.SecondPlayerPlayerCard.getInttype())) {
            if (j.FirstPlayerPlayCard.getNum() > j.SecondPlayerPlayerCard.getNum())
                j.Playerwin = j.playerFirst;
            else {
                j.Playerwin = j.playerFirst + 1;
                if (j.Playerwin == 2) {
                    j.Playerwin = 0;
                }
            }
        }
        //如果双方出不同花色
        else {
            //如果有王牌的话
            if (j.avoiratout) {
                //如果后手方出王牌的话
                if (Objects.equals(j.SecondPlayerPlayerCard.getInttype(), j.atout.getInttype())) {
                    j.Playerwin = j.playerFirst + 1;
                    if (j.Playerwin == 2)
                        j.Playerwin = 0;
                } else {//如果后手方不出王牌的话
                    j.Playerwin = j.playerFirst;
                }
            }
            //如果没有王牌的话（默认先手方赢）
            else {
                j.Playerwin = j.playerFirst;
            }

        }
        System.out.println("winner is " + (j.Playerwin + 1));
    }

}
