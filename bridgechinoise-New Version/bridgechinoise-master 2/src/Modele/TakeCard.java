package Modele;

import Controleur.IASimple;
import Controleur.IArandom;
import Controleur.IArandom2;

import java.util.Scanner;

public class TakeCard {
    Jeu j;
    Histoire h;

    public TakeCard(Jeu j, Histoire h) {
        this.j = j;
        this.h = h;
    }


    public Jeu playerWinTakeCard(){
        //赢家拿牌
        System.out.println("Maintenant c'est le tour de Jouer"+(j.Playerwin+1));
        System.out.println(("Donned le index que vous voulez prendre"));
        //输入
        Scanner input=new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if(index==-1){
            j = h.returnHistoire();
            return j;
        }
        //如果输入有误，重新输入
        while(j.pilescard[index].size()==0){
            System.out.println(("L'erreur, reessayer"));
            input=new Scanner(System.in);
            index = input.nextInt();
        }
        //把赢家的牌放到手牌里头
        Brand card = j.pilescard[index].get(0);
        j.pilescard[index].remove(0);
        j.playercard[j.Playerwin].add(card);
        j.TurnProcess++;
        j.showCard();
        return j;
    }
    public Jeu playerLoseTakeCard(){
        int a=j.Playerwin+1;
        if (a==2) a=0;
        System.out.println("Maintenant c'est le tour de Jouer"+(a+1));
        System.out.println(("Donned le index que vous voulez prendre"));
        //输入
         Scanner input=new Scanner(System.in);
        int index = input.nextInt();
        //回退历史记录
        if(index==-1){
            j = h.returnHistoire();
            return j;
        }
        //如果输入有误，重新输入
        while(j.pilescard[index].size()==0){
            System.out.println(("L'erreur, reessayer"));
            input=new Scanner(System.in);
            index = input.nextInt();
        }
        //把输家的牌放到手牌里头
        Brand card = j.pilescard[index].get(0);
        j.pilescard[index].remove(0);
        j.playercard[a].add(card);
        j.TurnProcess++;
        j.showCard();
        return j;
    }

    public void IAtakecard(Jeu j, int IA){
        int index=-1;
        switch (IA){
            case 1:
                IArandom iar=new IArandom(j);
                index = iar.IarandomGetCard();
                break;
            case 2:
                IASimple ias= new IASimple(j);
                index = ias.IASimpleTakeCard();
                break;
            case 3:
                IArandom2 iar2=new IArandom2(j);
                index = iar2.IarandomGetCard2();
                break;
        }
        System.out.println(index);
        Brand card = j.pilescard[index].get(0);
        System.out.println("IA prendre"+card.toString() + index);
        j.pilescard[index].remove(0);
        j.playercard[j.playerNow].add(card);
        j.TurnProcess++;
//        j.playercard[1].add(card);
        j.showCard();
    }

}
